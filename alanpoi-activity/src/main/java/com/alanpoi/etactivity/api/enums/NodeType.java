package com.alanpoi.etactivity.api.enums;

public enum NodeType {
    START("start", "开启节点"),
    MIDDLE("middle", "中间节点"),
    END("end", "结束节点");

    public String value;
    public String desc;

    NodeType(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
