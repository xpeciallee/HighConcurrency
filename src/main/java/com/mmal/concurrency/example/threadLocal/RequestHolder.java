package com.mmal.concurrency.example.threadLocal;

public class RequestHolder {
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();
    
    //在map中存储线程名，以及value 这里就是id
    public static void add(Long id ){
        requestHolder.set(id);
    }
    //根据线程名取出id
    public static Long getId(){
        return requestHolder.get();
    }
    //移出数据
    public static void remove(){
        requestHolder.remove();
    }
}
