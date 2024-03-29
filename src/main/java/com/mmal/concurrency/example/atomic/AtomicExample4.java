package com.mmal.concurrency.example.atomic;

import com.mmal.concurrency.annoations.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

@ThreadSafe
public class AtomicExample4 {
    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0, 2); //2
        count.compareAndSet(0, 1); //no
        count.compareAndSet(1, 3); //no
        count.compareAndSet(2, 4); //4
        count.compareAndSet(3, 5); //no
        System.out.println(count.get());
    }
}
