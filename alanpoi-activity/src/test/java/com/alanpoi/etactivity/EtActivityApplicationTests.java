package com.alanpoi.etactivity;

import com.alanpoi.etactivity.api.EtFlowApi;
import com.alanpoi.etactivity.api.req.CreateInstanceReq;
import com.alanpoi.etactivity.api.req.FlowProcessReq;
import com.alanpoi.etactivity.api.rsp.InstanceInfoRsp;
import com.alanpoi.etactivity.transfer.ExecutorTools;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
class EtActivityApplicationTests {
    @Resource
    private EtFlowApi etFlowApi;

    @Test
    void contextLoads() {
        long begin = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss.SSS");
        System.out.println(format.format(new Date()));
        CreateInstanceReq req = new CreateInstanceReq();
        req.setProcessId(27638201013239997L);
        req.setApplicant(123456L);
        req.setStatus(1001);
        req.setTaskName("XXX流程");
        req.setCreatedBy(123456L);
        InstanceInfoRsp result = etFlowApi.start(req);
//        boolean bool=etFlowApi.addHandler(new ArrayList<>());
//        String result2 = etFlowApi.endFlow("12334");
//        for (int i=0;i<50;i++){
//            ExecutorTools.getExecutor().execute(()->{
//                String result = etFlowApi.start("12334");
//                String result2 = etFlowApi.endFlow("12334");
//                System.out.println(result);
//                System.out.println(result2);
//            });
//        }
        System.out.println(String.format("结果:%s-%s,----耗时:%s", result, 1, System.currentTimeMillis() - begin));
    }

    @Test
    public void process() {
        FlowProcessReq req = new FlowProcessReq();
        req.setHandler(123456L);
        req.setTaskId(30333642257465940L);
        req.setIsAgree((byte) 1);
        req.setNextNodeId(2L);
        etFlowApi.process(req);
    }

}
