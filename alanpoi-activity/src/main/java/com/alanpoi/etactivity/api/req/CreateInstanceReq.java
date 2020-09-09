package com.alanpoi.etactivity.api.req;

import java.io.Serializable;

public class CreateInstanceReq implements Serializable {

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
     * 创建人
     */
    private Long createdBy;

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

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

}
