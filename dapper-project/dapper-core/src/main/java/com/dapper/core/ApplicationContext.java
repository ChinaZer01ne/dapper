package com.dapper.core;

import com.dapper.beans.BeanDefinition;
import com.dapper.beans.BeanDefinitionHolder;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        // 实例化所有的Bean
        initBeans();
        // 创建web服务
        onRefresh();
        // 启动web服务
        finishRefresh();
    }

    protected void finishRefresh() {

    }

    protected void onRefresh() {

    }


    private void initBeans() {
        Set<BeanDefinitionHolder> beanDefinitionHolders = componentScanner.scan("");
        // 反射获取实例对象
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            instanceMap.put(beanDefinitionHolder.getBeanName(), reflectObtain(beanDefinitionHolder.getBeanDefinition()));
        }
    }

    /**
     * 反射获取所有实例类
     * @param beanDefinition :
     * @return java.lang.Object
     * @throws
     */
    private Object reflectObtain(BeanDefinition beanDefinition) {
        final Class<? extends BeanDefinition> clazz = beanDefinition.getClass();
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
