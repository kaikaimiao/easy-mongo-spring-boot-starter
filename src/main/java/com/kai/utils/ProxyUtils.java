package com.kai.utils;

/**
 * @author kai
 * @version 1.0
 * @className ProxyUtils
 * @description 代理util
 * @date 2023/11/29 17:02
 */

import java.lang.reflect.Proxy;

public class ProxyUtils {
    private static final String CGLIB_CLASS_SEPARATOR = "$$";

    public static boolean isProxyClass(Class<?> clazz) {
        return Proxy.isProxyClass(clazz) || clazz.getName().contains(CGLIB_CLASS_SEPARATOR);
    }
}
