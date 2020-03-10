package com.qizhidao.alanpoi.common;

import com.qizhidao.alanpoi.excel.ApplicationUtil;
import com.qizhidao.alanpoi.excel.ExcelHelper;
import com.qizhidao.alanpoi.excel.ExcelParser;
import com.qizhidao.alanpoi.excel.handle.ExcelWorkbookManage;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public ExcelHelper getExcelHelper() {
        return new ExcelHelper();
    }

    @Bean
    public ApplicationUtil getApplicationUtil() {
        return new ApplicationUtil();
    }

    @Bean
    public ExcelParser getExcelParser(ExcelHelper excelHelper,
                                      ExcelWorkbookManage excelWorkbookManage,
                                      AbstractExcelService excelService,
                                      StringRedisTemplate redisTemplate,
                                      ExecutorTools executorTools) {
        return new ExcelParser(excelHelper, excelWorkbookManage, excelService, redisTemplate, executorTools);
    }


}
