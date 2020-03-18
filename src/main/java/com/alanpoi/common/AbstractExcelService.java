package com.alanpoi.common;

import lombok.extern.slf4j.Slf4j;


/**
 * @author zhuoxun.peng
 * 扩展服务类
 * 用户业务系统上传记录其他服务（如同步导入记录到框架）
 */
@Slf4j
public abstract class AbstractExcelService {


    /**
     * 初始化日志
     *
     * @return
     */
    public abstract Long initImportLogEntity(String jsonString);


    /**
     * 上传文件到框架
     *
     * @param jsonString
     * @param fileContent
     */
    public abstract void uploadFileToFrame(String jsonString, byte[] fileContent);


    /**
     *
     * @param jsonString
     */
    public abstract void uploadFileToFrame(String jsonString);
}
