package com.wjs.netty.netty3;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author wenjs
 */
public class NettyClientHandler extends ChannelDuplexHandler {

    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof RPCMessage)) {
            return;
        }

        RPCMessage rpcMessage = (RPCMessage)msg;
        System.out.println(rpcMessage.getMessage());
    }
}
