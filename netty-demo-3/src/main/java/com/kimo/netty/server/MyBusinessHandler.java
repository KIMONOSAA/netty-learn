package com.kimo.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MyBusinessHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (cause instanceof java.io.IOException && cause.getMessage().contains("reset")) {
            System.out.println("Connection was reset by the client.");
        } else {
            cause.printStackTrace();
        }
        ctx.close(); // 关闭通道
    }
}