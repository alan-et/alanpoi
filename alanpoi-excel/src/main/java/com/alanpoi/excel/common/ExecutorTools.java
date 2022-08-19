package com.alanpoi.excel.common;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuoxun.peng
 * @since 2020-2-25
 * <p>
 * 异步工具
 */

public class ExecutorTools {

    //一般的executor
    private static volatile ThreadPoolExecutor executor;

    private static volatile ThreadPoolExecutor dbExecutor;


    public static Executor getExecutor() {
        if (executor == null) {
            synchronized (ExecutorTools.class) {
                if (executor == null) {
                    executor = new ThreadPoolExecutor(2, 10, 300, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
                }
            }
        }
        return executor;
    }

    public static ThreadPoolExecutor getSyncDbExecutor() {
        if (dbExecutor == null) {
            synchronized (ExecutorTools.class) {
                if (dbExecutor == null) {
                    dbExecutor = new ThreadPoolExecutor(2, 10, 300, TimeUnit.SECONDS,
                            new LinkedBlockingDeque<>(), new DefaultThreadFactory("db"));
                }
            }
        }
        return dbExecutor;
    }
}
