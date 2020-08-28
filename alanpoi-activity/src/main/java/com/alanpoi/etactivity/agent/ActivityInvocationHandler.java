package com.alanpoi.etactivity.agent;

import com.alanpoi.common.util.ApplicationUtil;
import com.alanpoi.etactivity.client.EtSocketClient;
import com.alanpoi.etactivity.protocol.EtActivityEntity;
import com.alanpoi.etactivity.protocol.EtActivityReq;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Proxy Handler
 *
 * @author pengzhuoxun
 * @since 1.3.0
 */
@Component
public class ActivityInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        EtSocketClient etSocketClient = ApplicationUtil.getBean(EtSocketClient.class);
        if (etSocketClient == null) {
            throw new BeanCreationException(null, "etSocketClient", "EtSocketClient is null");
        }
        EtActivityReq req=new EtActivityReq();
        EtActivityEntity etActivityEntity = new EtActivityEntity();
        etActivityEntity.setClassName(method.getDeclaringClass().getName());
        etActivityEntity.setMethodName(method.getName());
        etActivityEntity.setParameterTypes(method.getParameterTypes());
        etActivityEntity.setParam(args);
        byte[] body=etActivityEntity.toByteArray();
        req.setVersion(1);
        req.setCmd((byte) (0x01));
        req.setBody(body);
        req.setLength(body.length);
        return etSocketClient.send(req.toByteArray());
    }
}
