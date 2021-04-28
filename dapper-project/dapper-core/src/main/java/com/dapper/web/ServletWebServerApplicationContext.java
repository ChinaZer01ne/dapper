package com.dapper.web;

import com.dapper.core.ApplicationContext;

/**
 * servlet容器
 * @author peach
 * @since 2021/4/28 0:05
 */
public class ServletWebServerApplicationContext extends ApplicationContext {

    private WebServer webServer;

    @Override
    protected void onRefresh() {
        createWebServer();
    }

    @Override
    protected void finishRefresh() {
        startWebServer();
    }

    /**
     * 创建web服务
     * @return void
     * @throws
     */
    private void createWebServer() {
        webServer = new TomcatWebServer();
    }

    /**
     * 创建web服务
     * @return void
     * @throws
     */
    private void startWebServer() {
        webServer.start();
    }

}
