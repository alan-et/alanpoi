package com.alanpoi.common;

import com.alanpoi.excel.imports.ApplicationUtil;
import com.alanpoi.excel.imports.ExcelInitConfig;
import com.alanpoi.excel.imports.ExcelParser;
import com.alanpoi.excel.imports.handle.ExcelWorkbookManage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;


/**
 * 初始化配置
 *
 * @author zhuoxun.peng
 * @since 2020-2-26
 */
@Configuration
public class InitConfig {

    @Bean
    public ExcelWorkbookManage getExcelWorkbookManage() {
        return new ExcelWorkbookManage();
    }

    @Bean
    public ExcelInitConfig getExcelHelper() {
        return new ExcelInitConfig();
    }

    @Bean
    public ApplicationUtil getApplicationUtil() {
        return new ApplicationUtil();
    }

    @Bean
    public ExcelParser getExcelParser(ExcelInitConfig excelInitConfig,
                                      ExcelWorkbookManage excelWorkbookManage,
                                      AbstractExcelService excelService,
                                      StringRedisTemplate redisTemplate,
                                      ExecutorTools executorTools) {
        return new ExcelParser(excelInitConfig, excelWorkbookManage, excelService, redisTemplate, executorTools);
    }


}
