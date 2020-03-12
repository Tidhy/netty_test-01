package com.netty.test_02;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyServer {


    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //因为使用的是NIO传输，所以在这里指定NioServerSocketChannel.class
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    //这里的类自己实现，当一个新的链接被接受时，就会创建一个类
                    //这个类会把接受到的数据添加到此类的Pipeline中
                    .childHandler(new MyServerInitializer());

            //在这里绑定下服务器
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            //该程序将会阻塞等待直到服务器的Channel关闭
            channelFuture.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
