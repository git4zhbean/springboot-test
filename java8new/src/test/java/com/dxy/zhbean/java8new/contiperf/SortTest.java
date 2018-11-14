package com.dxy.zhbean.java8new.contiperf;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2018/11/12
 */
public class SortTest implements Runnable {
    private String num;


    public SortTest(int num) {
        this.num = num + "";
    }

    public SortTest() {
    }

    public static void main(String[] args){
       int[] nums = {11,343,899,45,22,9,3223,6789};
        for (int i = 0; i < nums.length; i++) {
            new Thread(new SortTest(nums[i])).start();
        }

    }


    @Override
    public void run() {
        try {
            Thread.sleep(Integer.parseInt(num));
            System.out.println(num);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
