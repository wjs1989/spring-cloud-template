package com.wjs.netty.netty3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author wenjs
 */
public class ProtocolV1Encoder extends MessageToByteEncoder {


    /**
     * 把 message转换为byte
     *
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf out) throws Exception {
        if (msg instanceof RPCMessage) {
            RPCMessage rpcMessage = (RPCMessage) msg;

            ByteBuf byteBufBody = Unpooled.copiedBuffer(rpcMessage.getMessage(), CharsetUtil.UTF_8);
            byte[] body = new byte[byteBufBody.readableBytes()];
            byteBufBody.readBytes(body);

            int fullLenght = body.length + 2;

            out.writeInt(fullLenght);
            out.writeByte(rpcMessage.getCode());
            out.writeByte(rpcMessage.getType());
            out.writeBytes(body);

        }
    }

}