package com.dapper.web;

import com.dapper.beans.BeanDefinition;
import com.dapper.core.DapperLoader;

import java.util.Collection;
import java.util.List;

/**
 * @author peach 
 * @since 2021/4/28 22:29
 */
public class BeanDefinitionLoader implements DapperLoader<BeanDefinition> {

    @Override
    public List<BeanDefinition> load(Collection<Class<?>> collection) {
        return null;
    }
}
