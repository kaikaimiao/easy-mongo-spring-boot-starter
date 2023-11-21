package com.kai.core.wrapper.base;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 查询条件封装
 * <p>嵌套</p>
 *
 * @author kai
 * @date 2023/6/13
 */
public interface Nested<P, R> extends Serializable {

    /**
     * 嵌套条件构造器
     *
     * @param func 嵌套条件
     * @return 当前条件构造器
     */
    default R and(Function<P, P> func) {
        return and(true, func);
    }

    /**
     * 嵌套条件构造器 使用or连接
     *
     * @return 当前条件构造器
     */
    /**
     * 嵌套条件构造器
     *
     * @param func 嵌套条件
     * @return 当前条件构造器
     */
    default R or(Function<P, P> func) {
        return or(true, func);
    }

    /**
     * 嵌套条件构造器
     *
     * @param condition 是否开启嵌套
     * @param func      嵌套条件
     * @return 当前条件构造器
     */
    R or(boolean condition, Function<P, P> func);

    /**
     * 嵌套条件构造器
     *
     * @param condition 是否开启嵌套
     * @param func      嵌套条件
     * @return 当前条件构造器
     */
    R and(boolean condition, Function<P, P> func);

}
