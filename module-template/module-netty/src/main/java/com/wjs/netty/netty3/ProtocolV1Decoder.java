package com.wjs.netty.netty3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.List;

/**
 * @author wenjs
 */
public class ProtocolV1Decoder extends LengthFieldBasedFrameDecoder {

    public ProtocolV1Decoder() {
        this(8 * 1024 * 1024);//8M
    }

    public ProtocolV1Decoder(int maxFrameLength) {
        super(maxFrameLength, 0, 4, 0, 0);
    }

    /**
     * 把byte转换为消息
     *
     * @param ctx
     * @param in
     * @return
     * @throws Exception
     */
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        Object decode = super.decode(ctx, in);

        if (decode instanceof ByteBuf) {
            ByteBuf read = (ByteBuf) decode;
            int fullLenght = read.readInt();

            byte code = read.readByte();
            byte type = read.readByte();

            byte[] body = new byte[fullLenght - 2];
            read.readBytes(body);

            String message = new String(body);
            RPCMessage rpcMessage = new RPCMessage();
            rpcMessage.setCode(code);
            rpcMessage.setType(type);
            rpcMessage.setMessage(message);

            return rpcMessage;
        }
        return decode;
    }

}