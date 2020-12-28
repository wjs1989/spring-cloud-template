package com.wjs.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.CharsetUtil;

import java.util.Locale;

public class EchoClientHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /*channel活跃后，做业务处理*/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String request = "Hello,Netty";
        TextWebSocketFrame tsg = new TextWebSocketFrame(request.toUpperCase(Locale.CHINA));
        ctx.writeAndFlush(tsg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext cht, TextWebSocketFrame msg) throws Exception {

        String request = "get->" + msg.text();

        TextWebSocketFrame tsg = new TextWebSocketFrame(request.toUpperCase(Locale.CHINA));
        cht.writeAndFlush(tsg);
        System.out.println(request);
    }
}
