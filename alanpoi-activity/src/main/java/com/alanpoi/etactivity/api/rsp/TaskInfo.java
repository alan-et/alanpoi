package com.alanpoi.etactivity.api.rsp;

import java.util.Date;
import java.util.List;

public class TaskInfo {

    /**
     * 实例ID
     */
    private Long instanceId;

    /**
     * 节点ID
     */
    private Long nodeId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 当前处理人
     */
    private List<Long> currentHandler;

    /**
     * 创建人
     */
    private Long createdBy;

    private Date creationTime;

    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public List<Long> getCurrentHandler() {
        return currentHandler;
    }

    public void setCurrentHandler(List<Long> currentHandler) {
        this.currentHandler = currentHandler;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
