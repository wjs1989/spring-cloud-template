package com.wjs.netty.netty2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Locale;
import java.util.Objects;

/**
 * @ClassName MessageHander
 * @Description: TODO
 * @Author wjs
 * @Date 2020/11/26
 * @Version V1.0
 **/
public class Message2Handler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
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
//            channelGroup.writeAndFlush(
//                    new TextWebSocketFrame("Client"+ctx.channel()+" joined"));
//            channelGroup.add(ctx.channel());

        }
    }

    //客户端创建的时候触发，当客户端连接上服务端之后，就可以获取该channel，然后放到channelGroup中进行统一管理
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channelGroup.add(ctx.channel());
    }

    //客户端销毁的时候触发，
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //当handlerRemoved 被触发时候，channelGroup会自动移除对应的channel
        //clients.remove(ctx.channel());
        System.out.println("客户端断开，当前被移除的channel的短ID是：" +ctx.channel().id().asShortText());
    }


    public static void main(String[] args) {
        System.out.println((Objects.equals(3, 3)));
    }
}
