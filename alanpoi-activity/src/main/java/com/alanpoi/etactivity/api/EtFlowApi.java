package com.alanpoi.etactivity.api;

import com.alanpoi.etactivity.agent.annotation.IService;

@IService
public interface EtFlowApi {

    String startFlow(String flowId);
}
