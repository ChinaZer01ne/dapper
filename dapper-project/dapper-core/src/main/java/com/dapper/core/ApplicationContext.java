package com.dapper.core;

import com.dapper.beans.BeanDefinition;
import com.dapper.beans.BeanDefinitionHolder;
import com.dapper.context.annotation.ComponentScan;
import com.dapper.context.annotation.Configuration;
import com.dapper.web.BeanDefinitionLoader;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 上下文环境
 * @author peach
 * @since 2021/4/28 0:03
 */
public abstract class ApplicationContext {

    protected Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    private Map<String, Object> instanceMap = new HashMap<>();

    private ComponentScanner componentScanner;
    /**
     * 资源加载器
     */
    private BeanDefinitionLoader beanDefinitionLoader;

    public ApplicationContext() {
        componentScanner = new ComponentScanner();
        beanDefinitionLoader = new BeanDefinitionLoader();
    }
    /**
     * 初始化容器
     * @return void
     * @throws
     */
    public void refresh() {
        // 从配置类获取所有需要扫描路径
        processConfigBeanDefinitions();
        // 实例化所有的Bean
        initBeans();
        // 创建web服务
        onRefresh();
        // 启动web服务
        finishRefresh();
    }

    /**
     * 处理配置类，获取需要扫描的包路径
     * @return void
     * @throws
     */
    protected abstract void processConfigBeanDefinitions();

    protected void finishRefresh() {

    }

    protected void onRefresh() {

    }


    private void initBeans() {
        Set<BeanDefinitionHolder> beanDefinitionHolders = componentScanner.scan("");
        // 反射获取实例对象
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            instanceMap.put(beanDefinitionHolder.getBeanName(), reflectObtain(beanDefinitionHolder.getBeanDefinition()));
            beanDefinitionMap.put(beanDefinitionHolder.getBeanName(), beanDefinitionHolder.getBeanDefinition());
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

    /**
     * 向容器中加载资源
     * @param sourceClasses :
     * @return void
     * @throws
     */
    public void load(Collection<Class<?>> sourceClasses) {
        List<BeanDefinition> beanDefinitions = beanDefinitionLoader.load(sourceClasses);
        for (BeanDefinition beanDefinition : beanDefinitions) {
            beanDefinitionMap.put(beanDefinition.getBeanName(), beanDefinition);
        }
    }
}
