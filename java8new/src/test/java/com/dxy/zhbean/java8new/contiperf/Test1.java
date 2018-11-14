package com.dxy.zhbean.java8new.contiperf;

import org.junit.Test;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/11/12
 */
public class Test1 {


    @Test
    public void numselfIncrease(){
        int i = 0;
        i = i++;
        int j = i++;
        System.out.println("i: "+i);
        System.out.println("j: "+j);
    }


    /**
     * 遍历Map中元素的最佳方式
     */
    @Test
    public void testMap() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "zhangsan");
        map.put("2", "lisi");
        map.put("4", "dxy");

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry next = iterator.next();
            System.out.println(next.getKey() + " = " + next.getValue());
            iterator.remove();
        }

        System.out.println(map);

    }


    /**
     * 数组转ArrayList
     */
    @Test
    public void testArray2List() {
        String[] arr = {"dxy", "search", "list", "array"};
        List<String> list = Arrays.asList(arr);
        ArrayList<String> arrayList = new ArrayList<>(list);
        System.out.println(arrayList);
    }


    @Test
    public void testRandomNum() {

        int j = 1;
        while (j != 1000) {
            int min = 5;
            int max = 10;
            Random random = new Random();

            int i = random.nextInt((max - min) + 1) + min;
            System.out.print(i+" ");
            if (j % 50 == 0) {
                System.out.println();
            }
            j++;
        }
    }




}
