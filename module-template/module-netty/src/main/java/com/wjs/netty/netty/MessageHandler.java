package com.wjs.netty.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Locale;

/**
 * @ClassName MessageHander
 * @Description: TODO
 * @Author wjs
 * @Date 2020/11/26
 * @Version V1.0
 **/
public class MessageHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame msg) throws Exception {

        String request = "get->" + msg.text();

        TextWebSocketFrame tsg = new TextWebSocketFrame(request.toUpperCase(Locale.CHINA));
        System.out.println("get->" + msg.text());
        channelGroup.writeAndFlush(tsg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       // channelGroup.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        channelGroup.remove(ctx.channel());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx,
                                   Object evt) throws Exception {
        /*检查事件类型，如果是ws握手成功事件，群发通知*/
        if(evt == WebSocketServerProtocolHandler.
                ServerHandshakeStateEvent.HANDSHAKE_COMPLETE){
            channelGroup.writeAndFlush(
                    new TextWebSocketFrame("Client"+ctx.channel()+" joined"));
            channelGroup.add(ctx.channel());

        }
    }
}
