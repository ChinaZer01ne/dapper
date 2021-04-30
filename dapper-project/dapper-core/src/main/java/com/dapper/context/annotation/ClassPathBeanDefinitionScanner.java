package com.dapper.context.annotation;

import com.dapper.beans.BeanDefinition;
import com.dapper.beans.BeanDefinitionHolder;
import com.dapper.core.DapperScanner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 扫描class path下面的组件
 * @author peach
 * @since 2021/4/28 21:08
 */
public class ClassPathBeanDefinitionScanner implements DapperScanner<BeanDefinitionHolder> {

    public static final String CLASSPATH = "classpath:";

    public ClassLoader classPathClassLoader;
    @Override
    public Set<BeanDefinitionHolder> scan(String... basePackages) {
        Set<BeanDefinitionHolder> scannedBeanDefinitionHolder = new HashSet<>();
        final List<String> paths = Arrays.stream(basePackages).map(path -> CLASSPATH + path).collect(Collectors.toList());
        for (String path : paths) {
            scannedBeanDefinitionHolder.add(scan(path));
        }
        return scannedBeanDefinitionHolder;
    }

    @Override
    public BeanDefinitionHolder scan(String basePackage) {
        try {
            final Class<?> clazz = classPathClassLoader.loadClass(basePackage);
            final BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setClazz(clazz);
            beanDefinition.setBeanName(clazz.getName());
            return new BeanDefinitionHolder(beanDefinition, basePackage);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }
}
