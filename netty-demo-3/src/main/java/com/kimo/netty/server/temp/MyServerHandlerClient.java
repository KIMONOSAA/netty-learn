package com.kimo.netty.server.temp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MyServerHandlerClient extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("active");
        for (int i = 0; i < 5; i++){
            ctx.pipeline().writeAndFlush(Unpooled.copiedBuffer("我是蔡徐坤".getBytes()));
        }
    }

}
