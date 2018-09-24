package com.mmal.concurrency.example.syncContainer;

import com.mmal.concurrency.annoations.NotThreadSafe;

import java.util.Iterator;
import java.util.Vector;

@NotThreadSafe
public class VectorExample3 {


    //    java.util.ConcurrentModificationException并发修改异常 
    private static void test1(Vector<Integer> vector) {
        for (Integer i : vector) {
            if (i.equals(3)) {
                vector.remove(i);
            }
        }
    }

    //    java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> vector) {//使用迭代器iterator
        Iterator<Integer> iterator = vector.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                vector.remove(i);
            }
        }
    }

    private static void test3(Vector<Integer> vector) {
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i).equals(3)) {
                vector.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test1(vector);//ConcurrentModificationException
//        test2(vector);//ConcurrentModificationException
//        test3(vector);
    }
}

/*
* 异常信息
Exception in thread "main" java.util.ConcurrentModificationException
	at java.util.Vector$Itr.checkForComodification(Vector.java:1210)
	at java.util.Vector$Itr.next(Vector.java:1163)
	at com.mmal.concurrency.example.syncContainer.VectorExample3.test1(VectorExample3.java:14)
	at com.mmal.concurrency.example.syncContainer.VectorExample3.main(VectorExample3.java:45)

//所以别迭代器，做更新删除操作，会出现并发异常
//如果要做，则做好标记，在迭代器遍历完之后做
* */
