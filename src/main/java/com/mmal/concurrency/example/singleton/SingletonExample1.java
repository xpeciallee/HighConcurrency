package com.mmal.concurrency.example.singleton;

import com.mmal.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用的时候创建
 * 
 * */
@NotThreadSafe
public class SingletonExample1 {
    // 如果想要保证一个类只被初始化一次，那么默认的构造方法必须是私有的
    // 私有构造函数
    private SingletonExample1() {

    }

    // 定义一个单例对象
    // 每次返回的对象是同一个
    private static SingletonExample1 instance = null;

    //静态的工厂方法，来获取一个单例对象
    public static SingletonExample1 getInstance() {
        //单线程无问题，但是多线程可能有问题
        // 比如：两个线程同时执行，此时为null，那么就会实例化两次
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
