package com.mmal.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class ImmutableExample3 {
    private final static ImmutableList list = ImmutableList.of();
    
    private final static ImmutableSet set = ImmutableSet.copyOf(list);
    
    private final static ImmutableMap<Integer,Integer> map = ImmutableMap.of(1,2,3,4);
    private final static ImmutableMap<Integer,Integer> map2 = ImmutableMap.<Integer,Integer>builder().put(1,2).put(2,3).build();
    
    public static void main (String[] args){
//        list.
        System.out.println(map2.get(3));
    }
}
