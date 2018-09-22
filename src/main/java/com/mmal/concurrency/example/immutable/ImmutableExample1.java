package com.mmal.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.mmal.concurrency.annoations.NotThreadSafe;

import java.util.Map;
//目的能是变量是安全的就行


@NotThreadSafe
public class ImmutableExample1 {
    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
//        a  = 2 ;
//        b="3"
        //map = Map.newHashMap();
//        HashMap线程不安全
        map.put(1,3 );
        System.out.println(map.get(1));//3
    }
    
    private void test(final int a){
//        a = 1;
    }
}
