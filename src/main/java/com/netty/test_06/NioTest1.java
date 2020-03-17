package com.netty.test_06;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * 此类说明了nio是双向的，既可以读数据。又可以写数据，其中buffer.flip();为翻转数据的操作
 */
public class NioTest1 {
35
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);
        //capacity 缓冲区大小
        for (int i = 0; i < buffer.capacity(); i++) {
            //随机数
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }
        //翻转，buffer既可以读数据，又可以写数据
        buffer.flip();
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }

}
