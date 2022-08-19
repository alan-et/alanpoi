package test.ID;

import com.alanpoi.common.util.AlanList;
import com.alanpoi.common.util.ApplicationUtil;
import com.alanpoi.common.util.ID;
import com.alanpoi.common.util.ServerID;
import com.alanpoi.elasticsearch.ESOpType;
import com.alanpoi.elasticsearch.client.BatchRequest;
import com.alanpoi.elasticsearch.client.ESClientApi;
import com.alanpoi.test.Application;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.util.List;

@SpringBootTest(classes = Application.class)
public class IDTest {


    @Test
    public void test() throws IOException {
        List<Long> list = new AlanList<>();
        ID id = new ID(new ServerID(ApplicationUtil.getBean(StringRedisTemplate.class)));
        int i = 0;
        do {
            Long idLong = id.next();
            if (list.contains(idLong)) {
                System.out.println("重复:" + idLong);
                break;
            }
            if (idLong < 0) {
                System.out.println("出现负数:" + idLong);
                break;
            }
            System.out.println(idLong);
            list.add(idLong);
            i++;
        } while (i < 0x3fff);
//        System.out.println(Long.MAX_VALUE);
//        System.out.println(Long.valueOf((0x3ffffffffffL) << 21) | (0x3fff | 0L) << 7 | 0x7f);

    }
}
