package com.alanpoi.etactivity.api;

import com.alanpoi.etactivity.agent.annotation.IService;

import java.util.Collection;

@IService
public interface EtFlowApi {

    String init();

    String start(String activityId);

    boolean addHandler(Collection<?> collection);

    boolean reject();

    boolean recall();

    boolean batchProcessing(String... flowId);

    boolean batchReject(String... flowId);

    String endFlow(String flowId);
}
