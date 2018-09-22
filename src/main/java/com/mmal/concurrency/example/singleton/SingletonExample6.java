package com.mmal.concurrency.example.singleton;

import com.mmal.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载的时候创建
 * 但是当该类中的构造方法有太多处理的时候，效率会降低，当进行了类加载，但是很多方法不用，造成资源的浪费
 * 要求1：私有构造函数不能有太多处理
 * 要求2：类在实际过程中，肯定会被使用
 * */
@ThreadSafe
public class SingletonExample6 {
    // 如果想要保证一个类只被初始化一次，那么默认的构造方法必须是私有的
    // 私有构造函数
    private SingletonExample6() {

    }
    
    
    // 定义一个单例对象
    // 每次返回的对象是同一个
    private static SingletonExample6 instance = null;
    static {
        instance = new SingletonExample6();
    }

    //静态的工厂方法，来获取一个单例对象
    public static SingletonExample6 getInstance() {
        return instance;
    }
}
