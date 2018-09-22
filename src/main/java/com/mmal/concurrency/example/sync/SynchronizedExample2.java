package com.mmal.concurrency.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//如果有子类继承该类，子类调用方法2
//是带不上synchronized，是因为synchronized是不属于方法声明的一部分
public class SynchronizedExample2 {

    public static void test1(int j) {
        //修饰一个类,作用于该类，同一个类，不同对象，只能同时有一个对象调用
        synchronized (SynchronizedExample2.class) {
            for (int i = 1; i < 10; i++) {
                System.out.println(j+"test1-->"+i);
            }
        }
    }

    //修饰静态方法,作用于该类，同一个类，不同对象，只能同时有一个对象调用
    public static synchronized void test2(int j ){
        for (int i = 0; i < 10; i++) {
            System.out.println(j+"---test----"+i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.test1(1);
        });
        executorService.execute(()->{
            example2.test1(2);
        });
    }
}
