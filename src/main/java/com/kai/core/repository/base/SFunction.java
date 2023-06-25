package com.kai.core.repository.base;

import java.io.Serializable;

/**
 * 支持序列化的 Function
 * 为了获取字段名字
 *
 * @author kai
 * @date 2023/6/13
 */
@FunctionalInterface
public interface SFunction<T, R> extends Serializable {

    R apply(T t);

}
