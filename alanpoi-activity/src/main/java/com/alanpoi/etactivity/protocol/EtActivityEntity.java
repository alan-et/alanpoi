package com.alanpoi.etactivity.protocol;

public class EtActivityEntity<T> {

    private String className;
    private String methodName;
    private Class<?>[] parameterTypes;
    private T[] param;
    private Object result;

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
}
