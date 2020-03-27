package com.alanpoi.common.exception;

import com.alanpoi.common.enums.ResponseEnum;

/**
 *
 */
public class AlanPoiException extends RuntimeException{
    private Integer code;
    private String message;

    public AlanPoiException(int code, String msg){
        super(msg);
        this.code = code;
        this.message = msg;
    }
    public AlanPoiException(String msg) {
        super(msg);
        this.message=msg;
    }
    public AlanPoiException(ResponseEnum rspEnum) {
        super(rspEnum.message());
        this.code=rspEnum.status();
        this.message = rspEnum.message();
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

