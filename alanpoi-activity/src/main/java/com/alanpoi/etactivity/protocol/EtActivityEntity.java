package com.alanpoi.etactivity.protocol;


import com.alanpoi.common.util.ApplicationUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class EtActivityEntity<T> implements Serializable {


    private String className;
    private String methodName;
    private Class<?>[] parameterTypes;
    private T[] param;
    private Object result;

    public T getCls() {
        try {
            return (T) ApplicationUtil.getBean(Class.forName(className));
        } catch (Exception e) {

        }
        return null;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public T[] getParam() {
        return param;
    }

    public void setParam(T[] param) {
        this.param = param;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this);
        return byteArrayOutputStream.toByteArray();
    }
}
