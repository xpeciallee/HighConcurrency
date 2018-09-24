package com.mmal.concurrency.example.syncContainer;

import com.mmal.concurrency.annoations.NotThreadSafe;
import com.mmal.concurrency.annoations.ThreadSafe;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@NotThreadSafe
public class VectorExample2 {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }
            
            Thread thread1 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            };
            //get越界是因为remove引起的
            //当两句话remove，get ，都执行到同样的位置，会引起下列异常
            Thread thread2 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            };
            thread1.start();
            thread2.start();
        }
    }
}

/*
* 异常信息
* Exception in thread "Thread-15072" Exception in thread "Thread-15073" java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 5
	at java.util.Vector.get(Vector.java:751)
	at com.mmal.concurrency.example.syncContainer.VectorExample2$2.run(VectorExample2.java:33)
java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 5
	at java.util.Vector.remove(Vector.java:834)
	at com.mmal.concurrency.example.syncContainer.VectorExample2$1.run(VectorExample2.java:26)

* */