package com.zhbean.rwsplitting.bean;

import com.zhbean.rwsplitting.enums.DBTypeEnum;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2019/8/14 14:30
 */
public class DBContextHolder {

    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DBTypeEnum dbType) {
        contextHolder.set(dbType);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void master() {
        set(DBTypeEnum.MASTER);
        System.out.println("切换到master");
    }

    public static void slave() {
        int index = counter.getAndIncrement() % 2;
        if (counter.get() > 9999) {
            counter.set(-1);
        }
        if (index == 0) {
            set(DBTypeEnum.SLAVE1);
            System.out.println("切换到slave1");
        }else {
            set(DBTypeEnum.SLAVE2);
            System.out.println("切换到slave2");
        }
    }




}
