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
}
