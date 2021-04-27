package com.dapper.core;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 上下文环境
 * @author peach
 * @since 2021/4/28 0:03
 */
public class ApplicationContext {

    private Map<String, Object> instanceMap = new HashMap<>();

    private ComponentScanner componentScanner = new ComponentScanner();
    /**
     * 初始化容器
     * @return void
     * @throws
     */
    public void refresh() {
        List<Class<?>> scanClazzList = componentScanner.scan();
        // 反射获取实例对象
        for (Class<?> clazz : scanClazzList) {
            instanceMap.put(clazz.getName(), reflectObtain(clazz));
        }
    }

    /**
     * 反射获取所有实例类
     * @param clazz :
     * @return java.lang.Object
     * @throws
     */
    private Object reflectObtain(Class<?> clazz) {
        if (clazz == null) {
            throw new RuntimeException("类型不能为空！");
        }
        try {
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("类型不能为空！");
    }
}
