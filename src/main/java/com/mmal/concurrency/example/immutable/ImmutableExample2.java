package com.mmal.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.mmal.concurrency.annoations.ThreadSafe;

import java.util.Collections;
import java.util.Map;

@ThreadSafe
public class ImmutableExample2 {

    //没有final
    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);
    }
    
    public static void main(String[] args) {
        //运行操作，只是在操作的时候，会抛出异常
        map.put(1,3 );
        System.out.println(map.get(1));//3
    }
    
    private void test(final int a){
//        a = 1;
    }
}
