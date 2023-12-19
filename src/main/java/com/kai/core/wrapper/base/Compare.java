package com.kai.core.wrapper.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * 查询条件封装
 *
 * @author kai
 * @date 2023/6/13
 */
public interface Compare<R, P> extends Serializable {

    /**
     * eq 条件构建
     *
     * @param column 参与比较的列
     * @param val    比较值
     * @return 当前条件构建器
     */
    default R eq(P column, Object val) {
        return eq(true, column, val);
    }

    /**
     * ne 条件构建
     *
     * @param column 参与比较的列
     * @param val    比较值
     * @return 当前条件构建器
     */
    default R ne(P column, Object val) {
        return ne(true, column, val);
    }

    /**
     * le 条件构建
     *
     * @param column 参与比较的列
     * @param val    比较值
     * @return 当前条件构建器
     */
    default R le(P column, Object val) {
        return le(true, column, val);
    }

    /**
     * lt 条件构建
     *
     * @param column 参与比较的列
     * @param val    比较值
     * @return 当前条件构建器
     */
    default R lt(P column, Object val) {
        return lt(true, column, val);
    }

    /**
     * ge 条件构建
     *
     * @param column 参与比较的列
     * @param val    比较值
     * @return 当前条件构建器
     */
    default R ge(P column, Object val) {
        return ge(true, column, val);
    }

    /**
     * gt 条件构建
     *
     * @param column 参与比较的列
     * @param val    比较值
     * @return 当前条件构建器
     */
    default R gt(P column, Object val) {
        return gt(true, column, val);
    }

    /**
     * between 条件构建 左右均包括
     *
     * @param column 参与比较的列
     * @param leftV  左边比较值
     * @param rightV 右边比较值
     * @return 当前条件构建器
     */
    default R between(P column, Object leftV, Object rightV) {
        return between(true, column, leftV, rightV);
    }

    /**
     * in 条件构建
     *
     * @param column 参与比较的列
     * @param val    比较值
     * @return 当前条件构建器
     */
    default R in(P column, Collection<?> val) {
        return in(true, column, val);
    }



    /**
     * notIn 条件构建
     *
     * @param column 参与比较的列
     * @param val    比较值
     * @return 当前条件构建器
     */
    default R notIn(P column, Collection<?> val) {
        return notIn(true, column, val);
    }



    /**
     * regex 条件构建
     *
     * @param column 参与比较的列
     * @param val    比较值
     * @return 当前条件构建器
     */
    default R regex(P column, String val) {
        return regex(true, column, val);
    }

    /**


    /**
     * regex 条件构建
     *
     * @param column 参与比较的列
     * @param val    比较值
     * @param option 参数
     * @return 当前条件构建器
     */
    default R regex(P column, String val, String option) {
        return regex(true, column, val, option);
    }



    /**
     * 等于 条件构建
     *
     * @param condition 是否使用该条件进行构建
     * @param column    参与比较的列
     * @param val       比较值
     * @return 当前条件构建器
     */
    R eq(boolean condition, P column, Object val);



    /**
     * 等于 条件构建
     *
     * @param condition      是否使用该条件进行构建
     * @param columnSupplier 参与比较的列
     * @param val            比较值
     * @return 当前条件构建器
     */
    R eq(boolean condition, Supplier<String> columnSupplier, Object val);

    /**
     * ne 条件构建
     *
     * @param condition 是否使用该条件进行构建
     * @param column    参与比较的列
     * @param val       比较值
     * @return 当前条件构建器
     */
    R ne(boolean condition, P column, Object val);



    /**
     * exists 条件构建
     *
     * @param condition 是否使用该条件进行构建
     * @param column    参与比较的列
     * @param val       比较值
     * @return 当前条件构建器
     */
    R exists(boolean condition, P column, Boolean val);



    /**
     * le 条件构建
     *
     * @param condition 是否使用该条件进行构建
     * @param column    参与比较的列
     * @param val       比较值
     * @return 当前条件构建器
     */
    R le(boolean condition, P column, Object val);



    /**
     * lt 条件构建
     *
     * @param condition 是否使用该条件进行构建
     * @param column    参与比较的列
     * @param val       比较值
     * @return 当前条件构建器
     */
    R lt(boolean condition, P column, Object val);



    /**
     * ge 条件构建
     *
     * @param condition 是否使用该条件进行构建
     * @param column    参与比较的列
     * @param val       比较值
     * @return 当前条件构建器
     */
    R ge(boolean condition, P column, Object val);



    /**
     * gt 条件构建
     *
     * @param condition 是否使用该条件进行构建
     * @param column    参与比较的列
     * @param val       比较值
     * @return 当前条件构建器
     */
    R gt(boolean condition, P column, Object val);



    /**
     * between 条件构建 左右均包括
     *
     * @param condition 是否使用该条件进行构建
     * @param column    参与比较的列
     * @param leftV     左边比较值
     * @param rightV    右边比较值
     * @return 当前条件构建器
     */
    R between(boolean condition, P column, Object leftV, Object rightV);




    /**
     * in 条件构建
     *
     * @param condition 是否使用该条件进行构建
     * @param column    参与比较的列
     * @param val       比较值
     * @return 当前条件构建器
     */
    R in(boolean condition, P column, Collection<?> val);



    /**
     * notIn 条件构建
     *
     * @param condition 是否使用该条件进行构建
     * @param column    参与比较的列
     * @param val       比较值
     * @return 当前条件构建器
     */
    R notIn(boolean condition, P column, Collection<?> val);



    /**
     * regex 条件构建
     *
     * @param condition 是否使用该条件进行构建
     * @param column    参与比较的列
     * @param val       比较值
     * @return 当前条件构建器
     */
    default R regex(boolean condition, P column, String val) {
        return regex(condition, column, val, null);
    }




    /**
     * regex 条件构建
     *
     * @param condition 是否使用该条件进行构建
     * @param column    参与比较的列
     * @param val       比较值
     * @return 当前条件构建器
     */
    R regex(boolean condition, P column, String val, String option);

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
