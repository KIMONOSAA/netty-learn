package com.kimo.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

public class CustomStringDecoder extends ByteToMessageDecoder {
    private final Charset charset;

    public CustomStringDecoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        // 将 ByteBuf 解码为指定字符集的字符串
        out.add(in.toString(charset));
    }
}