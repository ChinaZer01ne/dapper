package com.dapper.web;

import com.dapper.beans.BeanDefinition;
import com.dapper.beans.BeanDefinitionHolder;
import com.dapper.context.annotation.ClassPathBeanDefinitionScanner;
import com.dapper.context.annotation.ComponentScan;
import com.dapper.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author peach
 * @since 2021/4/28 21:02
 */
public class AnnotationConfigServletWebServerApplicationContext extends ServletWebServerApplicationContext {

    private final ClassPathBeanDefinitionScanner scanner;

    public AnnotationConfigServletWebServerApplicationContext() {
        scanner = new ClassPathBeanDefinitionScanner();
    }

    @Override
    protected void processConfigBeanDefinitions() {
        List<BeanDefinitionHolder> configCandidateNames = new ArrayList<>();
        //List<String> scanPaths = new ArrayList<>();
        Set<String> candidateNames = beanDefinitionMap.keySet();
        for (String candidateName : candidateNames) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(candidateName);
            Class<?> clazz = beanDefinition.getClazz();
            if (clazz.getAnnotation(Configuration.class) != null) {
                configCandidateNames.add(new BeanDefinitionHolder(beanDefinition, candidateName));
            }
            if (clazz.getAnnotation(ComponentScan.class) != null) {
                final String[] basePackages = clazz.getAnnotation(ComponentScan.class).basePackages();
                // 扫描所有路径
                final Set<BeanDefinitionHolder> beanDefinitionHolders = scanner.scan(basePackages);
                beanDefinitionMap.putAll(beanDefinitionHolders.stream().collect(Collectors.toMap(BeanDefinitionHolder::getBeanName, BeanDefinitionHolder::getBeanDefinition)));
                //scanPaths.addAll(Arrays.asList(basePackages));
            }
        }
        // 需要读取候选的配置类，然后扫描包 TODO
        //for (BeanDefinitionHolder configCandidateName : configCandidateNames) {
        //    final Class<?> clazz = configCandidateName.getBeanDefinition().getClazz();
        //    if ()
        //}
    }
}
