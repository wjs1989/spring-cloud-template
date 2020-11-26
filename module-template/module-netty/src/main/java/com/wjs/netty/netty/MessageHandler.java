package com.wjs.netty.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @ClassName MessageHander
 * @Description: TODO
 * @Author wjs
 * @Date 2020/11/26
 * @Version V1.0
 **/
public class MessageHandler extends SimpleChannelInboundHandler<TextWebSockerFrame> {
    public static ChannelGroup channelGroup = new DefaultChannelGroup (GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSockerFrame msg) throws Exception {

        //收到消息
        TextWebSockerFrame tsg = new TextWebSockerFrame ();
        tsg.setMessage ("get->" + msg.getMessage ());
        System.out.println ("get->" + msg.getMessage ());
        channelGroup.writeAndFlush (tsg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channelGroup.add (ctx.channel ());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        channelGroup.remove (ctx.channel ());
    }


}
