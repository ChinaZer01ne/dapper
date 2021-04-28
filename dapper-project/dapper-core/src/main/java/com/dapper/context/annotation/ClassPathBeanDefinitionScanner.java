package com.dapper.context.annotation;

import com.dapper.beans.BeanDefinition;
import com.dapper.core.DapperScanner;
/**
 * 扫描class path下面的组件
 * @author peach
 * @since 2021/4/28 21:08
 */
public class ClassPathBeanDefinitionScanner implements DapperScanner<BeanDefinition> {

    @Override
    public BeanDefinition scan(String... basePackages) {
        return null;
    }
}
