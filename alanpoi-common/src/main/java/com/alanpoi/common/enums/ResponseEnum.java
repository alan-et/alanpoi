package com.alanpoi.common.enums;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public enum ResponseEnum {

    IMPORT_TEMP_EXP(31003, "导入模版异常"),
    IMPORT_FILE_DATA_EXP(31004, "导入结束,成功: %d 条,失败: %d 条，详情请下载日志查看!"),
    IMPORT_BUSINESS_ERROR_EXP(31005, "导入失败，请联系系统管理员!");

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
