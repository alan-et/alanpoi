package com.alanpoi.analysis.excel.exports;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ReflectPermission;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ReflectorManager {
    private static final Map<Class<?>, ReflectorManager> CACHE_REFLECTOR = new ConcurrentHashMap();
    private Map<String, Method> getMethods = new HashMap();
    private Map<String, Method> setMethods = new HashMap();
    private Map<String, Method> enumMethods = new HashMap();
    private List<Field> fieldList = new ArrayList();
    private Class<?> type;

    private ReflectorManager(Class<?> clazz) {
        this.type = clazz;
        this.addGetMethods(clazz);
        this.addFields(clazz);
        this.addSetMethods(clazz);
    }

    public static ReflectorManager forClass(Class<?> clazz) {
        return new ReflectorManager(clazz);
    }

    public static ReflectorManager fromCache(Class<?> clazz) {
        if (!CACHE_REFLECTOR.containsKey(clazz)) {
            CACHE_REFLECTOR.put(clazz, new ReflectorManager(clazz));
        }

        return (ReflectorManager)CACHE_REFLECTOR.get(clazz);
    }

    private void addGetMethods(Class<?> cls) {
        Map<String, List<Method>> conflictingGetters = new HashMap();
        Method[] methods = this.getClassMethods(cls);
        Method[] var4 = methods;
        int var5 = methods.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Method method = var4[var6];
            String name = method.getName();
            if (name.startsWith("get") && name.length() > 3) {
                if (method.getParameterTypes().length == 0) {
                    name = methodToProperty(name);
                    this.addMethodConflict(conflictingGetters, name, method);
                }
            } else if (name.startsWith("is") && name.length() > 2 && method.getParameterTypes().length == 0) {
                name = methodToProperty(name);
                this.addMethodConflict(conflictingGetters, name, method);
            }
        }

        this.resolveGetterConflicts(conflictingGetters);
    }

    private void resolveGetterConflicts(Map<String, List<Method>> conflictingGetters) {
        Iterator var2 = conflictingGetters.keySet().iterator();

        while(true) {
            while(var2.hasNext()) {
                String propName = (String)var2.next();
                List<Method> getters = (List)conflictingGetters.get(propName);
                Iterator<Method> iterator = getters.iterator();
                Method firstMethod = (Method)iterator.next();
                if (getters.size() == 1) {
                    this.addGetMethod(propName, firstMethod);
                } else {
                    Method getter = firstMethod;
                    Class getterType = firstMethod.getReturnType();

                    while(iterator.hasNext()) {
                        Method method = (Method)iterator.next();
                        Class<?> methodType = method.getReturnType();
                        if (methodType.equals(getterType)) {
                            throw new RuntimeException("Illegal overloaded getter method with ambiguous type for property " + propName + " in class " + firstMethod.getDeclaringClass() + ".  This breaks the JavaBeans specification and can cause unpredicatble results.");
                        }

                        if (!methodType.isAssignableFrom(getterType)) {
                            if (!getterType.isAssignableFrom(methodType)) {
                                throw new RuntimeException("Illegal overloaded getter method with ambiguous type for property " + propName + " in class " + firstMethod.getDeclaringClass() + ".  This breaks the JavaBeans specification and can cause unpredicatble results.");
                            }

                            getter = method;
                            getterType = methodType;
                        }
                    }

                    this.addGetMethod(propName, getter);
                }
            }

            return;
        }
    }

    private void addGetMethod(String name, Method method) {
        if (this.isValidPropertyName(name)) {
            this.getMethods.put(name, method);
        }

    }

    private void addSetMethods(Class<?> cls) {
        Map<String, List<Method>> conflictingSetters = new HashMap();
        Method[] methods = this.getClassMethods(cls);
        Method[] var4 = methods;
        int var5 = methods.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Method method = var4[var6];
            String name = method.getName();
            if (name.startsWith("set") && name.length() > 3 && method.getParameterTypes().length == 1) {
                name = methodToProperty(name);
                this.addMethodConflict(conflictingSetters, name, method);
            }
        }

        this.resolveSetterConflicts(conflictingSetters);
    }

    private static String methodToProperty(String name) {
        if (name.startsWith("is")) {
            name = name.substring(2);
        } else {
            if (!name.startsWith("get") && !name.startsWith("set")) {
                throw new RuntimeException("Error parsing property name '" + name + "'.  Didn't start with 'is', 'get' or 'set'.");
            }

            name = name.substring(3);
        }

        if (name.length() == 1 || name.length() > 1 && !Character.isUpperCase(name.charAt(1))) {
            name = name.substring(0, 1).toLowerCase(Locale.ENGLISH) + name.substring(1);
        }

        return name;
    }

    private void addMethodConflict(Map<String, List<Method>> conflictingMethods, String name, Method method) {
        List<Method> list = (List)conflictingMethods.get(name);
        if (list == null) {
            list = new ArrayList();
            conflictingMethods.put(name, list);
        }

        ((List)list).add(method);
    }

    private void resolveSetterConflicts(Map<String, List<Method>> conflictingSetters) {
        Iterator var2 = conflictingSetters.keySet().iterator();

        while(true) {
            while(var2.hasNext()) {
                String propName = (String)var2.next();
                List<Method> setters = (List)conflictingSetters.get(propName);
                Method firstMethod = (Method)setters.get(0);
                if (setters.size() == 1) {
                    this.addSetMethod(propName, firstMethod);
                } else {
                    Iterator<Method> methods = setters.iterator();
                    Method setter = null;

                    while(methods.hasNext()) {
                        Method method = (Method)methods.next();
                        if (method.getParameterTypes().length == 1) {
                            setter = method;
                            break;
                        }
                    }

                    if (setter == null) {
                        throw new RuntimeException("Illegal overloaded setter method with ambiguous type for property " + propName + " in class " + firstMethod.getDeclaringClass() + ".  This breaks the JavaBeans specification and can cause unpredicatble results.");
                    }

                    this.addSetMethod(propName, setter);
                }
            }

            return;
        }
    }

    private void addSetMethod(String name, Method method) {
        if (this.isValidPropertyName(name)) {
            this.setMethods.put(name, method);
        }

    }

    private void addFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Field[] var3 = fields;
        int var4 = fields.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Field field = var3[var5];
            if (this.canAccessPrivateMethods()) {
                try {
                    field.setAccessible(true);
                } catch (Exception var8) {
                }
            }

            if (field.isAccessible() && !"serialVersionUID".equalsIgnoreCase(field.getName())) {
                this.fieldList.add(field);
            }
        }

        if (clazz.getSuperclass() != null) {
            this.addFields(clazz.getSuperclass());
        }

    }

    private boolean isValidPropertyName(String name) {
        return !name.startsWith("$") && !"serialVersionUID".equals(name) && !"class".equals(name);
    }

    private Method[] getClassMethods(Class<?> cls) {
        HashMap<String, Method> uniqueMethods = new HashMap();

        for(Class currentClass = cls; currentClass != null; currentClass = currentClass.getSuperclass()) {
            this.addUniqueMethods(uniqueMethods, currentClass.getDeclaredMethods());
            Class<?>[] interfaces = currentClass.getInterfaces();
            Class[] var5 = interfaces;
            int var6 = interfaces.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Class<?> anInterface = var5[var7];
                this.addUniqueMethods(uniqueMethods, anInterface.getMethods());
            }
        }

        Collection<Method> methods = uniqueMethods.values();
        return (Method[])methods.toArray(new Method[methods.size()]);
    }

    private void addUniqueMethods(HashMap<String, Method> uniqueMethods, Method[] methods) {
        Method[] var3 = methods;
        int var4 = methods.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Method currentMethod = var3[var5];
            if (!currentMethod.isBridge()) {
                //returnType#MethodName:paramName
                String signature = this.getSignature(currentMethod);
                if (!uniqueMethods.containsKey(signature)) {
                    if (this.canAccessPrivateMethods()) {
                        try {
                            currentMethod.setAccessible(true);
                        } catch (Exception var9) {
                        }
                    }

                    uniqueMethods.put(signature, currentMethod);
                }
            }
        }

    }

    private String getSignature(Method method) {
        StringBuilder sb = new StringBuilder();
        Class<?> returnType = method.getReturnType();
        if (returnType != null) {
            sb.append(returnType.getName()).append('#');
        }

        sb.append(method.getName());
        Class<?>[] parameters = method.getParameterTypes();

        for(int i = 0; i < parameters.length; ++i) {
            if (i == 0) {
                sb.append(':');
            } else {
                sb.append(',');
            }

            sb.append(parameters[i].getName());
        }

        return sb.toString();
    }

    private boolean canAccessPrivateMethods() {
        try {
            SecurityManager securityManager = System.getSecurityManager();
            if (null != securityManager) {
                securityManager.checkPermission(new ReflectPermission("suppressAccessChecks"));
            }

            return true;
        } catch (SecurityException var2) {
            return false;
        }
    }

    public Method getGetMethod(String propertyName) {
        Method method = (Method)this.getMethods.get(propertyName);
        if (method == null) {
            throw new RuntimeException("There is no getter for property named '" + propertyName + "' in '" + this.type + "'");
        } else {
            return method;
        }
    }

    public Method getSetMethod(String propertyName) {
        Method method = (Method)this.setMethods.get(propertyName);
        if (method == null) {
            throw new RuntimeException("There is no getter for property named '" + propertyName + "' in '" + this.type + "'");
        } else {
            return method;
        }
    }

    public Object getValue(Object obj, String property) {
        Object value = null;
        Method m = (Method)this.getMethods.get(property);
        if (m != null) {
            try {
                value = m.invoke(obj);
            } catch (Exception var6) {
            }
        }

        return value;
    }

    public boolean setValue(Object obj, String property, Object object) {
        Method m = (Method)this.setMethods.get(property);
        if (m != null) {
            try {
                m.invoke(obj, object);
                return true;
            } catch (Exception var6) {
                return false;
            }
        } else {
            return false;
        }
    }

    public Map<String, Method> getGetMethods() {
        return this.getMethods;
    }

    public List<Field> getFieldList() {
        return this.fieldList;
    }

    public Object execEnumStaticMethod(String staticMethod, Object params) {
        if (!this.enumMethods.containsKey(this.setMethods)) {
            try {
                this.enumMethods.put(staticMethod, this.type.getMethod(staticMethod, params.getClass()));
            } catch (NoSuchMethodException var5) {
                throw new RuntimeException("There is no enum for property named '" + staticMethod + "' in '" + this.type + "'");
            }
        }

        try {
            return ((Method)this.enumMethods.get(staticMethod)).invoke((Object)null, params);
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }
}
