package com.alanpoi.etactivity;

import com.alanpoi.etactivity.api.EtFlowApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class EtActivityApplicationTests {
    @Resource
    private EtFlowApi etFlowApi;

    @Test
    void contextLoads() {
        etFlowApi.startFlow("12334");
    }

}
