package com.dapper.core;
/**
 * 扫描器
 * @author peach
 * @since 2021/4/28 21:07
 */
public interface DapperScanner<T> {

    /**
     * 扫描方法
     * @param basePackages :
     * @return T
     * @throws
     */
    T scan(String... basePackages);
}
