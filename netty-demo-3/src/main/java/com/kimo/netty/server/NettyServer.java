package com.kimo.netty.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 收发数据
 * @author kimo
 */
public class NettyServer {

    public static void main(String[] args) {
        new NettyServer().bind(7397);
    }

    private void bind(int port) {
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new MyChannelInitializer());

            ChannelFuture f = b.bind(port).sync();
            System.out.println("Server started on port: " + port);
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.err.println("Server interrupted: " + e.getMessage());
        } finally {
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }
    }
}


