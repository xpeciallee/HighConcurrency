package com.mmal.concurrency.example.singleton;

import com.mmal.concurrency.annoations.NotRecommend;
import com.mmal.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用的时候创建
 * 这种情况，是线程安全，但是因为加锁之后，性能降低
 * */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {
    // 如果想要保证一个类只被初始化一次，那么默认的构造方法必须是私有的
    // 私有构造函数
    
    private SingletonExample3() {

    }

    // 定义一个单例对象
    // 每次返回的对象是同一个
    private static SingletonExample3 instance = null;

    //静态的工厂方法，来获取一个单例对象
    public static synchronized SingletonExample3 getInstance() {
        //单线程无问题，但是多线程可能有问题
        // 比如：两个线程同时执行，此时为null，那么就会实例化两次
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
