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
        String result = etFlowApi.start("12334");
        System.out.println(result);
    }

}
