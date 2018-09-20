package com.mmal.concurrency.example.count;

import com.mmal.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


@Slf4j
@NotThreadSafe
public class CountExample1 {
     
    //请求总数
    public static int clientTotal = 5000;

    //允许的同时并发的执行线程数
    public static int threadTotal = 200;
    
    //计数器
    public static int count = 0;
    
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量,给定允许并发的数目(threadTotal)
        final Semaphore semaphore = new Semaphore(threadTotal);
        //定义闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(count);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) { 
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count = " + count);
    }
    
    private static void add(){
        count++;
    }
}
