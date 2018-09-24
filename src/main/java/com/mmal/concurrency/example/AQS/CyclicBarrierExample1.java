package com.mmal.concurrency.example.AQS;

import java.util.concurrent.*;

public class CyclicBarrierExample1 {
    //希望屏障的线程数是5个
    private static CyclicBarrier barrier = new CyclicBarrier(5);
    
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        //发出了10个请求，在延迟一秒后，执行相关操作
        for (int i = 0; i < 10 ; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(()->{
                try {
                    race(threadNum);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }
    
    private static void race(int ThreadNum) throws Exception{
        Thread.sleep(1000);
        System.out.println("ThreadNum"+ ThreadNum);
//        在barrier中屏障定义为5.表示执行到5个后向下执行
        try {
            barrier.await(2000, TimeUnit.MILLISECONDS);
        }catch (BrokenBarrierException | TimeoutException e){
            e.printStackTrace();
        }
//        也有重构方法，用等待时间来表示。
//        barrier.await(1000,TimeUnit.SECONDS)
        System.out.println("continue");
    }
}
