package com.alanpoi.etactivity.api.rsp;

public class InstanceInfoRsp {

    /**
     * 流程ID
     */
    private Long processId;

    /**
     * 任务名称（生成的待办名称）
     */
    private String taskName;

    /**
     * 申请人
     */
    private Long applicant;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 任务节点信息
     */
    private TaskInfo taskInfo;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 最近修改人
     */
    private Long lastUpdatedBy;

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getApplicant() {
        return applicant;
    }

    public void setApplicant(Long applicant) {
        this.applicant = applicant;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public TaskInfo getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(TaskInfo taskInfo) {
        this.taskInfo = taskInfo;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
