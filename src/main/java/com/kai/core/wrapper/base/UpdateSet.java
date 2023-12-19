package com.kai.core.wrapper.base;

import java.io.Serializable;

/**
 * 查询条件封装
 *
 * @author kai
 * @date 2023/6/13
 */
public interface UpdateSet<R, P> extends Serializable {

    /**
     * set 条件构建
     *
     * @param column 修改的列
     * @param val    修改的值
     * @return 当前条件构建器
     */
    default R set(P column, Object val) {
        return set(true, column, val);
    }


    /**
     * eq 条件构建
     *
     * @param column 参与比较的列
     * @param val    比较值
     * @return 当前条件构建器
     */
    R set(boolean condition, P column, Object val);

    /**
     * 给你当前条件的字段拼接一个子字段 嵌段文档使用
     *
     * @param sFunction 嵌套对象属性的get方法
     * @param <N>       子对象类型
     * @param <E>       属性类型
     * @return 当前条件构造器
     */
    default <N, E> R setChild(SFunction<N, E> sFunction) {
        return setChild(true, sFunction);
    }

    ;

    /**
     * 给你当前条件的字段拼接一个子字段 嵌段文档使用
     *
     * @param sFunction 嵌套对象属性的get方法
     * @param <N>       子对象类型
     * @param <E>       属性类型
     * @return 当前条件构造器
     */
    <N, E> R setChild(boolean condition, SFunction<N, E> sFunction);

}
