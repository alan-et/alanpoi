package com.alanpoi.etactivity.agent;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * FactoryBean proxy
 *
 * @author pengzhuoxun
 * @since 1.3.0
 */

@Component
public class ActivityFactoryBean<T> implements FactoryBean<T> {
    private Class<T> cls;

    @Override
    public T getObject() throws Exception {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{cls}, new ActivityInvocationHandler());
    }

    @Override
    public Class<?> getObjectType() {
        return cls;
    }

    public Class<T> getCls() {
        return cls;
    }

    public void setCls(Class<T> cls) {
        this.cls = cls;
    }
}
