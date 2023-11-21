package com.kai.core.wrapper;

/**
 * Wrapper 条件构造
 *
 * @author kai
 * @date 2023/6/13
 */
public final class Wrappers {
    /**
     * 获取查询条件构造器
     *
     * @param <T> 实体类类型
     * @return 查询条件构造器
     */
    public static <T> LambdaQueryWrapper<T> lambdaQuery() {
        return new LambdaQueryWrapper<>();
    }

    /**
     * 获取修改条件构造器
     *
     * @param <T> 实体类类型
     * @return 修改条件构造器
     */
    public static <T> LambdaUpdateWrapper<T> lambdaUpdate() {
        return new LambdaUpdateWrapper<>();
    }
}
