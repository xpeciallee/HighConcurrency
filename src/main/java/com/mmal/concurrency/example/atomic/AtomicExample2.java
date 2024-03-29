package com.mmal.concurrency.example.atomic;

import com.mmal.concurrency.annoations.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
public class AtomicExample2 {
    //请求总数
    public static int clientTotal = 5000;

    //允许的同时并发的执行线程数
    public static int threadTotal = 200;

    //计数器
    public static AtomicLong count = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
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
                //在计数器到达0之前，await方法会一直阻塞
                
                countDownLatch.countDown();
            });
        }
        //释放所有线程
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count = " + count.get());
    }

    private static void add(){
        count.incrementAndGet();
//        count.getAndIncrement()
    }
}
