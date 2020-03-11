package com.alanpoi.excel;

import lombok.Data;

import java.io.Serializable;

/**
 * 记录错误文件信息
 * @author zhuoxun.peng
 */
@Data
public class ErrorFile implements Serializable {


    /**对应的excel对象*/
    private String workbookId;
    /**文件所在的服务器地址*/
    private String ipAddress;
    /**文件所在的服务器地址的目录*/
    private String filePath;
    /**文件名*/
    private String fileName;

    public ErrorFile() {
    }

    public ErrorFile(String workbookId, String ipAddress, String filePath, String fileName) {
        this.workbookId = workbookId;
        this.ipAddress = ipAddress;
        this.filePath = filePath;
        this.fileName = fileName;
    }
}
