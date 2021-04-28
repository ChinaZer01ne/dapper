package com.dapper.core;

import java.util.Collection;
import java.util.List;

/**
 * 加载器接口
 * @author peach
 * @since 2021/4/28 22:29
 */
public interface DapperLoader<T> {

    /**
     * 加载方法
     * @param collection :
     * @return T
     * @throws
     */
    List<T> load(Collection<Class<?>> collection);
}
