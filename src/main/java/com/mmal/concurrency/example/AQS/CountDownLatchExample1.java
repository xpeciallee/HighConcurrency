package com.mmal.concurrency.example.AQS;

import javax.sound.midi.Soundbank;
import java.util.concurrent.*;

public class CountDownLatchExample1 {

    private static int threadCount = 200;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        //await函数需要等待countDown里面的函数减到0才会运行下面的代码执行
        
        countDownLatch.await();
        //给定时间的等待，超过时间就不等待直接执行
        countDownLatch.await(10, TimeUnit.MICROSECONDS);
        System.out.println("finish");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        System.out.println(threadNum);
        Thread.sleep(100);
    }
}
