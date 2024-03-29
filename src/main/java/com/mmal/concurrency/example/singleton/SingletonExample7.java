package com.mmal.concurrency.example.singleton;

import com.mmal.concurrency.annoations.Recommend;
import com.mmal.concurrency.annoations.ThreadSafe;

/**
 * 枚举模式：最安全
 * */
@ThreadSafe
@Recommend
public class SingletonExample7 {
    
    //私有构造函数
    private SingletonExample7(){
    }
    
    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }
    
    private enum Singleton{
        INSTANCE;
        
        private SingletonExample7 singleton;
        
        // jvm保证这个方法绝对只被调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }
        public SingletonExample7 getInstance(){
            return singleton;
        }
    }
}
