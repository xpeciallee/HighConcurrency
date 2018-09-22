package com.mmal.concurrency.example.count;

import com.mmal.concurrency.annoations.NotThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;



@NotThreadSafe
public class CountExample1 {
    //请求总数
    public static int clientTotal = 5000;

    //允许的同时并发的执行线程数
    public static int threadTotal = 200;
    
    //计数器
    public static int count = 0;
    
    public static void main(String[] args) throws InterruptedException {
        //  定义线程池，该线程池，是根据线程增长的，
        //  Executors.newFixedThreadPool(10);表示线程池的线程上线为10
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量,给定允许并发的数目(threadTotal)
        final Semaphore semaphore = new Semaphore(threadTotal);
        //定义闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

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
