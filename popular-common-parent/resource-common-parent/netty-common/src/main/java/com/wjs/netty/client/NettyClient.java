package com.wjs.netty.client;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @author wenjs
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        NettyClientBootstrap bootstrap = new NettyClientBootstrap();
        bootstrap.init();
        Channel channel = bootstrap.getNewChannel(new InetSocketAddress("127.0.0.1", 9999));

        int i = 3;
        while (i-- > 0) {
            channel.writeAndFlush(Unpooled.copiedBuffer(
                    "你好", CharsetUtil.UTF_8)).addListener(l -> {
                System.out.println("++++++++++++++");
            });
            Thread.sleep(1000);
        }
        bootstrap.closeChannel(channel);

        bootstrap.shutdown();
        Thread.sleep(1000);
    }

}
