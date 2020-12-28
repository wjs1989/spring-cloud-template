package com.wjs.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/**
 * 作者：Mark/Maoke
 * 创建日期：2018/08/25
 * 类说明：基于Netty的服务器
 */
@Slf4j
//@Component
public class EchoServer {

    private final int port = 8088;

    @PostConstruct
    public void init() {
        Thread thread = new Thread(() -> {
            log.info("启动NettyServer");
            /*线程组*/
            EventLoopGroup group = new NioEventLoopGroup();
            EventLoopGroup boss = new NioEventLoopGroup();
            try {
                /*服务端启动必备*/
                ServerBootstrap b = new ServerBootstrap();
                b.group(boss, group)
                        .channel(NioServerSocketChannel.class)/*指定使用NIO的通信模式*/
                        .localAddress(new InetSocketAddress(port))/*指定监听端口*/
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new HttpServerCodec());
                                ch.pipeline().addLast(new ChunkedWriteHandler());
                                ch.pipeline().addLast(new HttpObjectAggregator(8192));
                                /*支持ws数据的压缩传输*/
                                ch.pipeline().addLast(new WebSocketServerCompressionHandler());
                                ch.pipeline().addLast(new WebSocketServerProtocolHandler("/ws", "WebSocket", true, 65536 * 10));
                                ch.pipeline().addLast(new MessageHandler());
                            }
                        });

                /*异步绑定到服务器，sync()会阻塞到完成*/
                ChannelFuture f = b.bind().sync();
                f.channel().closeFuture().sync();/*阻塞当前线程，直到服务器的ServerChannel被关闭*/
            } catch (InterruptedException e) {
            } finally {
                try {
                    group.shutdownGracefully().sync();
                    boss.shutdownGracefully().sync();
                } catch (InterruptedException e) {
                }
            }
        });
        thread.setDaemon(true);
        thread.setName("netty.server");
        thread.start();
    }


}
