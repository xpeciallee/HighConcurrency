package com.mmal.concurrency.example.atomic;

import com.mmal.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;


@Slf4j
@ThreadSafe
public class AtomicExample6 {

    public static AtomicBoolean isHappened = new AtomicBoolean(false);

    public static int clientTotal = 5000;

    public static int threadTotal = 200;


    //让某段代码只执行一次
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量,给定允许并发的数目(threadTotal)
        final Semaphore semaphore = new Semaphore(threadTotal);
        //定义闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count = " + isHappened.get());
    }

    private static void test() {
        if (isHappened.compareAndSet(false, true)) {
            System.out.println("execute");
        }
    }
}
