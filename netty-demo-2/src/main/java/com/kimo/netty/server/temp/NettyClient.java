package com.kimo.netty.server.temp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

    public static void main(String[] args) {
        new NettyClient().connect("192.168.101.1", 7397);
    }

    private void connect(String inetHost, int inetPort) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.handler(new MyChannelInitializerClient());
            ChannelFuture f = b.connect(inetHost, inetPort).sync();
            System.out.println("Client connected to server: " + inetHost + ":" + inetPort);
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.err.println("Connection interrupted: " + e.getMessage());
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
