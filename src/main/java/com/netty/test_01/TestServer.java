package com.netty.test_01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 虽然这只是一个简单的Demo，但是几乎每一个Netty的流程基本上都是这样的
 *
 */
public class TestServer {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 基于NIO的事件循环组
         * bossGroup接收连接，把接收到的链接发送给workerGroup，相当于老板
         * workerGroup完成链接对应的处理，相当于员工
         * 这两个事件循环组都是死循环
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            /**
             * ServerBootstrap Netty提供帮助服务端启动的class，服务器中关联上面的那两个事件循环
             * 服务器启动的时候，TestServerInitializer相当于处理器，里面都是自定义的
             */
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());
            //定义端口
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            //优雅的关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
