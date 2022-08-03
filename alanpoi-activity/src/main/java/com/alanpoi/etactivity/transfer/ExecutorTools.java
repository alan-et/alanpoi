package com.alanpoi.etactivity.transfer;

import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuoxun.peng
 * @since 2020-4-3
 * <p>
 * 异步工具
 */

@Component
public class ExecutorTools {

    private static ThreadPoolExecutor executor;


    ExecutorTools() {
        executor = new ThreadPoolExecutor(2, 10, 300, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
    }

    public static Executor getExecutor() {
        return executor;
    }

}
