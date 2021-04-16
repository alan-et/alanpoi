package com.alanpoi.analysis.common;

import com.alanpoi.analysis.excel.exports.WorkbookManager;
import com.alanpoi.analysis.excel.exports.handle.ExportHandle;
import com.alanpoi.analysis.excel.imports.ExcelInitConfig;
import com.alanpoi.analysis.excel.imports.ExcelParser;
import com.alanpoi.analysis.excel.imports.handle.ExcelWorkbookManage;
import com.alanpoi.analysis.word.handle.WordHandle;
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
    public WorkbookManager getWorkbookManage() {
        return new WorkbookManager();
    }

    @Bean
    public ExcelInitConfig getExcelHelper() {
        return new ExcelInitConfig();
    }

    @Bean
    public ExportHandle getExportHandle() {
        return new ExportHandle();
    }

    @Bean
    public WordHandle getWordHandle() {
        return new WordHandle();
    }

    @Bean
    public ExcelParser getExcelParser(ExcelInitConfig excelInitConfig,
                                      ExcelWorkbookManage excelWorkbookManage,
                                      StringRedisTemplate redisTemplate,
                                      ExecutorTools executorTools) {
        return new ExcelParser(excelInitConfig, excelWorkbookManage, redisTemplate, executorTools);
    }


}
