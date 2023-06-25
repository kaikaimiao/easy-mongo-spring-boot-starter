package com.kai.core.wrapper;

/**
 * Wrapper 条件构造
 *
 * @author kai
 * @date 2023/6/13
 */
public final class Wrappers {

    public static <T> LambdaQueryWrapper<T> lambdaQuery() {
        return new LambdaQueryWrapper<>();
    }

}
