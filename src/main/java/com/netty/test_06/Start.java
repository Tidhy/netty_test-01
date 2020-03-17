package com.netty.test_06;

import java.lang.reflect.Method;

public class Start {

    class A {
        private String name = "张三";

        private Integer age = 1;

        public String getName(String name,Integer age) {
            return name;
        }
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class<?> c = A.class;
        Method method = c.getMethod("getName", String.class,Integer.class);
        System.out.println(method);
    }


}
