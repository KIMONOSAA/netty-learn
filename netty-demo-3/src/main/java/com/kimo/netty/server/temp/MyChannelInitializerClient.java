package com.kimo.netty.server.temp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class MyChannelInitializerClient extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        if (socketChannel.localAddress() != null) {
            System.out.println("Local address: " + socketChannel.localAddress().getHostString());
        } else {
            System.out.println("Local address is not available yet.");
        }

        socketChannel.pipeline().addLast(new MyServerHandlerClient());
    }
}
