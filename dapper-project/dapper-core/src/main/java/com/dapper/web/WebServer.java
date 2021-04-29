package com.dapper.web;

/**
 * @author peach
 * @since 2021/4/28 13:41
 */
public interface WebServer {
    /**
     * 启动web服务
     * @return void
     * @throws
     */
    void start();
    /**
     * 关闭web容器
     * @return void
     * @throws
     */
    void shutdown();
    /**
     * 端口
     * @return void
     * @throws
     */
    int getPort();
}