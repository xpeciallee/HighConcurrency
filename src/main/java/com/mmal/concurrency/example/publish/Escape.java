package com.mmal.concurrency.example.publish;
//对象溢出

import com.mmal.concurrency.annoations.NotRecommend;
import com.mmal.concurrency.annoations.NotThreadSafe;

@NotThreadSafe
@NotRecommend
public class Escape {
    private int thisCannBeEscape = 0;
    
    public Escape(){
        new InnerClass();
    }
    
    public class InnerClass{
        public InnerClass(){
            System.out.println(Escape.this.thisCannBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
