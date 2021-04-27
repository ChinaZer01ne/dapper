package com.dapper;

import com.dapper.core.ApplicationContext;
import com.dapper.core.ServletWebServerApplicationContext;

/**
 * 这个类可以用来启动一个Dapper应用程序
 * @author peach
 * @since 2021/4/27 23:42
 */
public class DapperApplication {

    /**
     * 启动Dapper应用
     * @return com.dapper.DapperApplication
     */
    public static ApplicationContext run() {
        // 初始化IOC
        ApplicationContext context = createApplicationContext();
        context.refresh();
        // DI
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
        return new ServletWebServerApplicationContext();
    }
}
