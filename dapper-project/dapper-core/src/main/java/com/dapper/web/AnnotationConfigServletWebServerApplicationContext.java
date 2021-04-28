package com.dapper.web;

import com.dapper.context.annotation.ClassPathBeanDefinitionScanner;

/**
 * @author peach
 * @since 2021/4/28 21:02
 */
public class AnnotationConfigServletWebServerApplicationContext extends ServletWebServerApplicationContext {

    private final ClassPathBeanDefinitionScanner scanner;

    public AnnotationConfigServletWebServerApplicationContext() {
        scanner = new ClassPathBeanDefinitionScanner();
    }

}
