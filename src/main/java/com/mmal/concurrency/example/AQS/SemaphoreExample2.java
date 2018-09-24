package com.mmal.concurrency.example.AQS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample2 {

    private static int threadCount = 20;

    public static void main(String[] args) throws Exception {
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        //给定允许的并发数
        
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire(3);//获取3个许可
                    test(threadNum);
                    semaphore.release(3);//释放3个许可
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            });
        }
        System.out.println("finish");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
       
        System.out.println(threadNum);
        Thread.sleep(1000);
    }
}
