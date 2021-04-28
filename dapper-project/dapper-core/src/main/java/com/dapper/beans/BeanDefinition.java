package com.dapper.beans;

/**
 * 对象实例的抽象
 * @author peach
 * @since 2021/4/28 15:25
 */
public class BeanDefinition {

    /**
     * 当前bean的类型
     */
    private Class<?> clazz;
    /**
     * 当前bean的名称
     */
    private String beanName;

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}