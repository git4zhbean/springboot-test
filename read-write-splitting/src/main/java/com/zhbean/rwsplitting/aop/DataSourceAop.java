package com.zhbean.rwsplitting.aop;

import com.zhbean.rwsplitting.bean.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2019/7/17 14:43
 */
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("(execution(* com.zhbean.rwsplitting.service..*.select*(..)) " +
            "|| execution(* com.zhbean.rwsplitting.service..*.get*(..)))" +
            "|| execution(* com.zhbean.rwsplitting.service..*.find*(..))")
    public void readPointCut() {

    }

    @Pointcut("execution(* com.zhbean.rwsplitting.service..*.add*(..))" +
            "|| execution(* com.zhbean.rwsplitting.service..*.insert*(..)) " +
            "|| execution(* com.zhbean.rwsplitting.service..*.update*(..))" +
            "|| execution(* com.zhbean.rwsplitting.service..*.edit*(..)) " +
            "|| execution(* com.zhbean.rwsplitting.service..*.delete*(..)) " +
            "|| execution(* com.zhbean.rwsplitting.service..*.remove*(..))")
    public void writePointCut() {
    }

    @Before("readPointCut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointCut()")
    public void write() {
        DBContextHolder.master();
    }

}
