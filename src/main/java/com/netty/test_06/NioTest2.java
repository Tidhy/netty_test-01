package com.netty.test_06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/***
 * 传统的流，如果转换成Nio的操作
 * 不管是读，还是写，buffer对象必须要有的，不可以直接通过channel读或者写
 */
public class NioTest2 {

    public static void main(String[] args) throws Exception {
        //获取文件的输入流
        FileInputStream fileInputStream = new FileInputStream("nio.txt");

        //通过输入流，获取文件的Channel并把信息写到Buffer中
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);

        //操作翻转
        byteBuffer.flip();

        //读取每一个字节，最后关闭掉
        while (byteBuffer.remaining() > 0) {
            byte b = byteBuffer.get();
            System.out.println("Character：" + (char) b);
        }
        fileInputStream.close();

    }
}
