package com.alanpoi.xml.common;

import com.alanpoi.common.util.ID;
import com.alanpoi.common.util.StringUtils;


public class Media {

    private String id;

    private String base64;

    private String url;

    private String fileName;

    private Integer width;

    private Integer height;

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

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
