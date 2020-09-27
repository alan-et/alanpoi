package com.alanpoi.etactivity.api.req;

import java.io.Serializable;
import java.util.Map;

public class FlowProcessReq implements Serializable {

    /**
     * 流程实例ID，和任务ID必须存在一个
     */
    private Long instanceId;

    /**
     * 流程任务ID，和流程ID必须存在一个
     */
    private Long taskId;

    /**
     * 不走路由，手动指定下一节点
     */
    private Long nextNodeId;

    /**
     * 是否同意 0/1
     */
    private byte isAgree;

    /**
     * 处理人
     */
    private Long handler;

    /**
     * 处理流程填写的备注
     */
    private String remark;

    /**
     * 扩展参数，可用于路由判断
     */
    private Map<String, String> extParam;

    //TODO 支持附件


    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public byte getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(byte isAgree) {
        this.isAgree = isAgree;
    }

    public Long getHandler() {
        return handler;
    }

    public void setHandler(Long handler) {
        this.handler = handler;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Map<String, String> getExtParam() {
        return extParam;
    }

    public void setExtParam(Map<String, String> extParam) {
        this.extParam = extParam;
    }

    public Long getNextNodeId() {
        return nextNodeId;
    }

    public void setNextNodeId(Long nextNodeId) {
        this.nextNodeId = nextNodeId;
    }
}
