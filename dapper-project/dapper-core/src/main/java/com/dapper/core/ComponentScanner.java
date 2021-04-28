package com.dapper.core;

import com.dapper.beans.BeanDefinitionHolder;

import java.util.List;
import java.util.Set;

/**
 * 读取扫描需要使用到的组件
 * @author peach
 * @since 2021/4/28 0:12
 */
public class ComponentScanner {

    /**
     * 扫描所有组件类
     * @return java.util.List<java.lang.Class>
     * @throws
     */
    public Set<BeanDefinitionHolder> scan(String... basePackages) {
        return doScan(basePackages);
    }

    /**
     * 扫描
     * @param basePackages :
     * @return java.util.Set<com.dapper.beans.BeanDefinitionHolder>
     * @throws
     */
    private Set<BeanDefinitionHolder> doScan(String[] basePackages) {
        return null;
    }
}
