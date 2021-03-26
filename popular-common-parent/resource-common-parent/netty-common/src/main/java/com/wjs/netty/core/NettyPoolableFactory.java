package com.wjs.netty.core;

import com.wjs.netty.client.NettyClientBootstrap;
import com.wjs.netty.util.NetUtil;
import io.netty.channel.Channel;
import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author wenjs
 */
public class NettyPoolableFactory implements KeyedPoolableObjectFactory<NettyPoolKey, Channel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyPoolableFactory.class);
    private final NettyClientBootstrap clientBootstrap;

    public NettyPoolableFactory(NettyClientBootstrap clientBootstrap) {
        this.clientBootstrap = clientBootstrap;
    }

    @Override
    public Channel makeObject(NettyPoolKey key) throws Exception {
        InetSocketAddress inetSocketAddress = NetUtil.toInetSocketAddress(key.getAddress());
        Channel newChannel = clientBootstrap.getNewChannel(inetSocketAddress);
        return newChannel;
    }

    @Override
    public void destroyObject(NettyPoolKey key, Channel channel) throws Exception {
        if (channel != null) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("will destroy channel:" + channel);
            }
            channel.disconnect();
            channel.close();
        }
    }

    @Override
    public boolean validateObject(NettyPoolKey key, Channel obj) {
        if (obj != null && obj.isActive()) {
            return true;
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("channel valid false,channel:" + obj);
        }
        return false;
    }


    @Override
    public void activateObject(NettyPoolKey key, Channel obj) throws Exception {

    }

    @Override
    public void passivateObject(NettyPoolKey key, Channel obj) throws Exception {

    }
}
