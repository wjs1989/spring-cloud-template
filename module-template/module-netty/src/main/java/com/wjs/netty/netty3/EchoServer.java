package com.wjs.netty.netty3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * 作者：Mark/Maoke
 * 创建日期：2018/08/25
 * 类说明：基于Netty的服务器
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 9999;
        EchoServer echoServer = new EchoServer(port);
        System.out.println("服务器即将启动");
        echoServer.start();
        System.out.println("服务器关闭");
    }

    public void start() throws InterruptedException {
        /*线程组*/
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            /*服务端启动必备*/
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)/*指定使用NIO的通信模式*/
                    .localAddress(new InetSocketAddress(port))/*指定监听端口*/
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            /**
                             * 心跳
                             */
                            ch.pipeline().addLast(new IdleStateHandler(0, 5, 0, TimeUnit.SECONDS));
                            ch.pipeline().addLast(new ProtocolV1Decoder());
                            ch.pipeline().addLast(new ProtocolV1Encoder());
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            ChannelFuture f = b.bind().sync();/*异步绑定到服务器，sync()会阻塞到完成*/
            f.channel().closeFuture().sync();/*阻塞当前线程，直到服务器的ServerChannel被关闭*/
        } finally {
            group.shutdownGracefully().sync();

        }


    }


}
