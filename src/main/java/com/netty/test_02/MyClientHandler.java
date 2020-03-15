package com.netty.test_02;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * 在这里，客户端和服务端channelRead0方法都有发送数据的命令，两者不停的互相发送消息，无限循环
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {


    /**
     * 同样的，服务端向客户端返回消息的时候，这个方法就会被调用
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
        System.out.println("client output:" + msg);
        // 向服务端发送当前时间
        ctx.writeAndFlush("from client:" + LocalDateTime.now());
    }

    /**
     * 当客户端与服务端连接成功之后，这个方法会自动调用
     * 在这里连接成功之后，让客户端向服务端发送数据，触发服务端的channelRead0方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       ctx.writeAndFlush("客户端向服务端发送的数据");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

























