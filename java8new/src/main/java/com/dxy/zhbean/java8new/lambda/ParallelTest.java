package com.dxy.zhbean.java8new.lambda;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/9/6
 */
public class ParallelTest {

    public static void main(String[] args){
        long[] longArr = new long[10000];

        Arrays.parallelSetAll(longArr, index-> ThreadLocalRandom.current().nextInt(500000));
        Arrays.stream(longArr).limit(10).forEach(i->System.out.print(i+" "));
        System.out.println();
        Arrays.parallelSort(longArr);
        Arrays.stream(longArr).limit(10).forEach(i->System.out.print(i+" "));
    }



}
