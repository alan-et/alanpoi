package com.qizhidao.alanpoi.common;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 */
@Slf4j
//@Component
public class NullExcelService extends AbstractExcelService{

    @Override
    public Long initImportLogEntity(String jsonString) {
        return null;
    }

    @Override
    public void uploadFileToFrame(String jsonString, byte[] fileContent) {

    }

    @Override
    public void uploadFileToFrame(String jsonString) {

    }
}
