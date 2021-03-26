package com.wjs.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author wenjs
 */
public class TestHandler extends SimpleChannelInboundHandler<ByteBuf> {

    ChannelHandlerContext cctx ;
    /*读取到网络数据后进行业务处理*/
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("client Accept"+msg.toString(CharsetUtil.UTF_8));
        //ctx.close();

    }

    /*channel活跃后，做业务处理*/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer(
                "Hello,Netty",CharsetUtil.UTF_8));
        cctx =ctx;
    }

    // @Override
    // public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    //     System.out.println("客户端循环心跳监测发送: "+new Date());
    //     if (evt instanceof IdleStateEvent){
    //         IdleStateEvent event = (IdleStateEvent)evt;
    //         if (event.state()== IdleState.WRITER_IDLE){
    //             ctx.writeAndFlush("biubiu");
    //         }
    //     }
    // }
}
