package com.wjs.netty.netty2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class NettyServer2 {
    private EventLoopGroup mainGroup ;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    public NettyServer2() {
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WssServerInitialzer());    //添加自定义初始化处理器
    }

    @PostConstruct
    public void start(){
        future = this.server.bind(8088);
        //System.err.println("netty 服务端启动完毕 .....");
    }

    @PreDestroy
    private void destory() throws Exception{
        mainGroup.shutdownGracefully();
        subGroup.shutdownGracefully();
       // System.out.println("关闭server");
    }
}
