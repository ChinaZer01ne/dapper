package com.dapper.core;

import java.util.Set;

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
    Set<T> scan(String... basePackages);
    /**
     * 扫描方法
     * @param basePackage :
     * @return T
     * @throws
     */
    T scan(String basePackage);
}
