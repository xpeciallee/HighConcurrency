package com.mmal.concurrency.example.atomic;

import com.mmal.concurrency.annoations.ThreadSafe;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;


@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {

        AtomicExample5 example5 = new AtomicExample5();

        if (updater.compareAndSet(example5, 100, 120)) {
            System.out.println(example5.count);
        }

        if (updater.compareAndSet(example5, 100, 120)) {
            System.out.println(example5.count);
        } else {
            System.out.println(example5.count);
        }
    }
}
