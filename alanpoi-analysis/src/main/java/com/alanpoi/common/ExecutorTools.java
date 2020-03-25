package com.alanpoi.common;

import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuoxun.peng
 * @since 2020-2-25
 *
 * 异步工具
 */

@Component
public class ExecutorTools {

    //一般的executor
    private ThreadPoolExecutor executor;

    private ThreadPoolExecutor dbExecutor;

    ExecutorTools(){
        executor =  new ThreadPoolExecutor(2, 10, 300, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        dbExecutor = new ThreadPoolExecutor(2, 10, 300, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(), new DefaultThreadFactory("db"));
    }

    public Executor getExecutor(){
        return executor;
    }

    public ThreadPoolExecutor getSyncDbExecutor() {
        return dbExecutor;
    }
}
