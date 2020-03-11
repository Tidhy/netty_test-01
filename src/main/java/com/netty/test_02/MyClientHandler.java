package com.netty.test_02;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;


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


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

























