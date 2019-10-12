package com.zhbean.rwsplitting.bean;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2019/8/14 14:25
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}
