package com.mmal.concurrency.example.singleton;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * 懒汉模式->双重同步锁单例模式
 * 单例实例在第一次使用的时候创建
 * 这种情况，是线程安全，但是因为加锁之后，性能降低
 * */
@NotThreadSafe
public class SingletonExample4 {
    // 如果想要保证一个类只被初始化一次，那么默认的构造方法必须是私有的
    // 私有构造函数
    
    private SingletonExample4() {

    }

     //在 new SingletonExample4的时候，cpu
    //1. memory = allocate()；分配内存空间
    //2. ctorInstance() 初始化对象
    //3. Instance = memory 设置instance指向刚分配的内存
    
    
    //在多线程情况下，发生指令重排。有安全隐患
    //JVM和cpu优化，发生了指令重排
    
    //1. memory = allocate()；分配内存空间
    //3. Instance = memory 设置instance指向刚分配的内存
    //2. ctorInstance() 初始化对象
    
    // 定义一个单例对象
    // 每次返回的对象是同一个
    private static SingletonExample4 instance = null;

    //静态的工厂方法，来获取一个单例对象
    //多线程AB执行
    public static  SingletonExample4 getInstance() {
        //单线程无问题，但是多线程可能有问题
        // 比如：两个线程同时执行，此时为null，那么就会实例化两次
        
        if (instance == null) {//双重检测机制  // 2. B执行这一步，发现不为空了，所以直接跳过，return null
            synchronized (SingletonExample4.class){//加上锁
                if(instance == null){
                    instance = new SingletonExample4();// 1. A执行到这一步 --->3,  但是2的初始化还没有进行
                }
            }
        }
        return instance;
    }
}
