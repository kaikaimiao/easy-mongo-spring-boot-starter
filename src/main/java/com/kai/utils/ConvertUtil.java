package com.kai.utils;

import com.kai.core.wrapper.base.SFunction;
import lombok.experimental.UtilityClass;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 转转换工具
 *
 * @author kai
 * @date 2023/6/13
 */
@UtilityClass
public class ConvertUtil {


    public static final String GET = "get";

    public static final String IS = "is";

    /**
     * 缓存方法应用对应的属性名称
     */
    private static final Map<Class<?>, String> CLASS_FIELD_META_MAP = new ConcurrentHashMap<>();

    /**
     * Converts method reference to field name
     */
    public static <T> String convertToFieldName(SFunction<T, ?> fn) {

        SerializedLambda lambda = getSerializedLambda(fn);
        return CLASS_FIELD_META_MAP.computeIfAbsent(fn.getClass(), aClass -> {
            String methodName = lambda.getImplMethodName();
            if (methodName.startsWith(GET)) {
                methodName = methodName.substring(3);
            } else if (methodName.startsWith(IS)) {
                methodName = methodName.substring(2);
            } else {
                throw new IllegalArgumentException("Invalid getter method: " + methodName);
            }
            String fieldMeta = StringUtils.firstToLowerCase(methodName);
            //id进行特殊判断
            if ("id".equals(fieldMeta)) {
                fieldMeta = "_id";
            }
            return fieldMeta;
        });
    }

    /**
     * 获取一个实现了序列化的lambda函数
     *
     * @param fn 目标函数
     * @return 实现了序列化的lambda函数
     */
    public static SerializedLambda getSerializedLambda(Serializable fn) {

        try {
            // Extract SerializedLambda and cache it
            Method method = fn.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(true);
            return (SerializedLambda) method.invoke(fn);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}