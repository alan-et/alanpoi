package com.qizhidao.alanpoi.common;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public enum ResponseEnum {

    IMPORT_FILE_DATA_EXP(31004, "导入结束,成功: %d 条,失败: %d 条，详情请下载日志查看!");


    private final int status;
    private final String message;

    ResponseEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int status() {
        return this.status;
    }

    public String message() {
        return this.message;
    }

    public String toString() {
        return StringUtils.join(new Serializable[]{this.status, ": ", this.message});
    }
}
