package test.elasticsearch;

import com.alanpoi.elasticsearch.ESOpType;
import com.alanpoi.elasticsearch.client.BatchRequest;
import com.alanpoi.elasticsearch.client.ESClientApi;
import com.alanpoi.test.Application;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest(classes = Application.class)
public class ElasticTest {

    @Autowired
    private ESClientApi esRestClient;

    @Test
    public void bulkTest() throws IOException {
        BatchRequest request=BatchRequest.build("qzd_cms","POST");
        request.add("6", ESOpType.index,"{\"name\":\"测试6\"}");
        JSONObject result= esRestClient.bulk(request);
        System.out.println(result);
    }

    @Test
    public void indexTest() throws IOException {
        JSONObject result=esRestClient.insertDoc("qzd_cms","5", JSON.parseObject("{\"name\":\"测试\"}"));
        System.out.println(result);
    }
}
