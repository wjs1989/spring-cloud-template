package com.wjs.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author wenjs
 */
public class NettyClientBootstrap {

    private final Bootstrap bootstrap = new Bootstrap();
    private final EventLoopGroup eventLoopGroupWorker = new NioEventLoopGroup();

    public NettyClientBootstrap() {
    }

    public void init() {
        bootstrap.group(eventLoopGroupWorker)
                .channel(NioSocketChannel.class)/*指定使用NIO的通信模式*/
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));
                    }
                });
    }

    public Channel getNewChannel(InetSocketAddress inetSocketAddress) throws InterruptedException {
        ChannelFuture f = bootstrap.connect(inetSocketAddress).sync();
        return f.channel();
    }

    public void shutdown() throws InterruptedException {
        eventLoopGroupWorker.shutdownGracefully().sync();
    }

    public void closeChannel(Channel channel) {
        channel.close();
    }

}
