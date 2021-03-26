package com.wjs.netty.core;

import com.wjs.netty.util.CollectionUtils;
import io.netty.channel.Channel;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * @author wenjs
 */
public class NettyClientChannelManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyClientChannelManager.class);
    /**
     * 连接池
     */
    private final GenericKeyedObjectPool<NettyPoolKey, Channel> nettyClientKeyPool;

    /**
     * 分段锁，
     * 在产生新address对应的channel过程中，使用分段锁保证线程安全
     */
    private final ConcurrentMap<String, Object> channelLocks = new ConcurrentHashMap<>();

    /**
     * pollKey缓存
     */
    private final ConcurrentMap<String, NettyPoolKey> poolKeyMap = new ConcurrentHashMap<>();

    /**
     * channel缓存
     */
    private final ConcurrentMap<String, Channel> channels = new ConcurrentHashMap<>();

    private Function<String, NettyPoolKey> poolKeyFunction;

    NettyClientChannelManager(final NettyPoolableFactory keyPoolableFactory,
                              final Function<String, NettyPoolKey> poolKeyFunction
                              ) {
        nettyClientKeyPool = new GenericKeyedObjectPool<>(keyPoolableFactory);
        nettyClientKeyPool.setConfig(getNettyPoolConfig());
        this.poolKeyFunction = poolKeyFunction;
    }

    private GenericKeyedObjectPool.Config getNettyPoolConfig(/*final NettyClientConfig clientConfig*/) {
        GenericKeyedObjectPool.Config poolConfig = new GenericKeyedObjectPool.Config();
        poolConfig.maxActive = 1;
        poolConfig.minIdle = 0;
        poolConfig.maxWait = 60 * 1000L;
        poolConfig.testOnBorrow = true;
        poolConfig.testOnReturn = true;
        poolConfig.lifo = true;
        return poolConfig;
    }

    Channel acquireChannel(String serverAddress) {
        Channel channelToServer = channels.get(serverAddress);
        if (channelToServer != null) {
            channelToServer = getExistAliveChannel(channelToServer, serverAddress);
            if (channelToServer != null) {
                return channelToServer;
            }
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("will connect to " + serverAddress);
        }
        Object lockObj = CollectionUtils.computeIfAbsent(channelLocks, serverAddress, key -> new Object());
        synchronized (lockObj) {
            return doConnect(serverAddress);
        }
    }

    void releaseChannel(Channel channel, String serverAddress) {
        if (channel == null || serverAddress == null) { return; }
        try {
            synchronized (channelLocks.get(serverAddress)) {
                Channel ch = channels.get(serverAddress);
                if (ch == null) {
                    nettyClientKeyPool.returnObject(poolKeyMap.get(serverAddress), channel);
                    return;
                }
                if (ch.compareTo(channel) == 0) {
                    if (LOGGER.isInfoEnabled()) {
                        LOGGER.info("return to pool, rm channel:{}", channel);
                    }
                    destroyChannel(serverAddress, channel);
                } else {
                    nettyClientKeyPool.returnObject(poolKeyMap.get(serverAddress), channel);
                }
            }
        } catch (Exception exx) {
            LOGGER.error(exx.getMessage());
        }
    }

    void destroyChannel(String serverAddress, Channel channel) {
        if (channel == null) { return; }
        try {
            if (channel.equals(channels.get(serverAddress))) {
                channels.remove(serverAddress);
            }
            nettyClientKeyPool.returnObject(poolKeyMap.get(serverAddress), channel);
        } catch (Exception exx) {
            LOGGER.error("return channel to rmPool error:{}", exx.getMessage());
        }
    }

    private Channel getExistAliveChannel(Channel rmChannel, String serverAddress) {
        if (rmChannel.isActive()) {
            return rmChannel;
        } else {
            int i = 0;
            for (; i < 3; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException exx) {
                    LOGGER.error(exx.getMessage());
                }
                rmChannel = channels.get(serverAddress);
                if (rmChannel != null && rmChannel.isActive()) {
                    return rmChannel;
                }
            }
            if (i == 3) {
                LOGGER.warn("channel {} is not active after long wait, close it.", rmChannel);
                releaseChannel(rmChannel, serverAddress);
                return null;
            }
        }
        return null;
    }

    private Channel doConnect(String serverAddress) {
        Channel channelToServer = channels.get(serverAddress);
        if (channelToServer != null && channelToServer.isActive()) {
            return channelToServer;
        }
        Channel channelFromPool = null;
        try {
            NettyPoolKey currentPoolKey = poolKeyFunction.apply(serverAddress);
            NettyPoolKey previousPoolKey = poolKeyMap.putIfAbsent(serverAddress, currentPoolKey);

            //从连接池中获取对象
            channelFromPool = nettyClientKeyPool.borrowObject(poolKeyMap.get(serverAddress));
            channels.put(serverAddress, channelFromPool);
        } catch (Exception exx) { 
        }
        return channelFromPool;
    }

    ConcurrentMap<String, Channel> getChannels() {
        return channels;
    }
}
