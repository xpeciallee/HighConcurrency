package com.mmal.concurrency.example.AQS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample4 {

    private static int threadCount = 20;

    public static void main(String[] args) throws Exception {
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        //给定允许的并发数
        //也就是允许多少个线程获取到许可
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    //尝试获取许可，如果能拿到许可就做，拿不到就丢弃
                    if(semaphore.tryAcquire(5, TimeUnit.SECONDS)) {//表示在5秒内获取到许可，获取不到就丢弃 
                        test(threadNum);
                        semaphore.release();//释放一个许可
                    } 
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
