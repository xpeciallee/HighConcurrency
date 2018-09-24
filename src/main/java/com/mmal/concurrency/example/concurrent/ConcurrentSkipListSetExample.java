package com.mmal.concurrency.example.concurrent;

import com.mmal.concurrency.annoations.ThreadSafe;

import java.util.Set;
import java.util.concurrent.*;

@ThreadSafe
public class ConcurrentSkipListSetExample {
    //请求总数
    public static int clientTotal = 5000;

    //允许的同时并发的执行线程数
    public static int threadTotal = 200;

    public static Set<Integer> set =new ConcurrentSkipListSet<>();

    public static void main(String[] args) throws InterruptedException {
        //  定义线程池，该线程池，是根据线程增长的，
        //  Executors.newFixedThreadPool(10);表示线程池的线程上线为10
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量,给定允许并发的数目(threadTotal)
        final Semaphore semaphore = new Semaphore(threadTotal);
        //定义闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("set.size---"+set.size());
    }
    private static void update(int i ){
        
        set.add(i);
    }
}
