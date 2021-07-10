package com.alanpoi.analysis.word;

import com.alanpoi.common.util.ID;
import com.alanpoi.common.util.StringUtils;

import java.util.UUID;

public class Media {

    private String id;

    private String base64;

    private String url;

    private String fileName;

    /**
     * .png .mp4等媒体格式
     */
    private String fileType;

    public String getId() {
        if (StringUtils.isBlank(id)) {
            id = String.valueOf(ID.getId().next());
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
