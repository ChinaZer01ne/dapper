package com.dapper;

import com.dapper.core.ApplicationContext;
import com.dapper.web.AnnotationConfigServletWebServerApplicationContext;
import com.dapper.web.ServletWebServerApplicationContext;

import java.util.Set;

/**
 * 这个类可以用来启动一个Dapper应用程序
 * @author peach
 * @since 2021/4/27 23:42
 */
public class DapperApplication {

    /**
     * 资源类
     */
    private Set<Class<?>> sourceClasses;

    public DapperApplication(Class<?>[] sourceClasses) {
        this.sourceClasses = Set.of(sourceClasses);
    }

    /**
     * 启动Dapper应用
     * @return com.dapper.DapperApplication
     */
    public static ApplicationContext run(Class<?>[] sourceClasses) {
        return new DapperApplication(sourceClasses).run();
    }

    /**
     * 启动Dapper应用
     * @return com.dapper.core.ApplicationContext
     * @throws
     */
    private ApplicationContext run() {
        // 初始化IOC
        ApplicationContext context = createApplicationContext();
        // 加载资源类
        context.load(sourceClasses);
        context.refresh();
        return context;
    }

    /**
     * 创建IOC容器
     * @return void
     * @throws
     */
    private static ApplicationContext createApplicationContext() {
        // 判断当前环境
        // 启动服务
        return new AnnotationConfigServletWebServerApplicationContext();
    }

    public Set<Class<?>> getSourceClasses() {
        return sourceClasses;
    }
}
