package com.alanpoi.etactivity.api;


import com.alanpoi.etactivity.agent.annotation.IService;
import com.alanpoi.etactivity.api.req.CreateInstanceReq;
import com.alanpoi.etactivity.api.req.FlowProcessReq;
import com.alanpoi.etactivity.api.rsp.InstanceInfoRsp;

//import javax.validation.constraints.NotNull;
import java.util.Collection;

@IService
public interface EtFlowApi {

    String init();

    /**
     * Create an instance and task, and return the instance and task object
     *
     * @param createInstanceReq
     * @return
     * @since 2020-9-8
     */
    InstanceInfoRsp start(CreateInstanceReq createInstanceReq);

    /**
     * Create an instance and task asynchronously, and return the instance id
     *
     * @param createInstanceReq
     * @return
     * @since 2020-9-8
     */
    Long asyncStart(CreateInstanceReq createInstanceReq);

    boolean process(FlowProcessReq flowProcessReq);

    boolean addHandler(Collection<?> collection);

//    boolean reject();

    boolean recall();

    boolean batchProcessing(String... flowId);

    boolean batchReject(String... flowId);

    String endFlow(String flowId);
}
