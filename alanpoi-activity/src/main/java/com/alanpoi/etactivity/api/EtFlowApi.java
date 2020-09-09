package com.alanpoi.etactivity.api;


import com.alanpoi.etactivity.agent.annotation.IService;
import com.alanpoi.etactivity.api.req.CreateInstanceReq;
import com.alanpoi.etactivity.api.rsp.InstanceInfoRsp;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@IService
public interface EtFlowApi {

    String init();

    InstanceInfoRsp start(@NotNull CreateInstanceReq createInstanceReq);

    Long asyncStart(@NotNull CreateInstanceReq createInstanceReq);

    boolean addHandler(Collection<?> collection);

    boolean reject();

    boolean recall();

    boolean batchProcessing(String... flowId);

    boolean batchReject(String... flowId);

    String endFlow(String flowId);
}
