package com.mmal.concurrency.example.sync;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//如果有子类继承该类，子类调用方法2
//是带不上synchronized，是因为synchronized是不属于方法声明的一部分
public class SynchronizedExample1 {

    // /修饰代码块，作用范围是代码块，作用对象是调用该方法的对象
    public void test1(int j) {
        synchronized (this) {
            for (int i = 1; i < 10; i++) {
                System.out.println(j+"test1-->"+i);
            }
        }
    }

    //修饰方法，作用范围是整个方法。作用对象是调用该方法的对象
    public synchronized void test2(){
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.test1(1);
        });
        executorService.execute(()->{
            example2.test1(2);
        });
    }
}
