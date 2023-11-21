package com.kai.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 字节码工具
 *
 * @author kai
 * @date 2023/6/13
 */
@UtilityClass
public class ClassFieldUtil {


    /**
     * 缓存mongo实体的主键字段
     */
    private static final Map<Class<?>, Field> FIELD_CACHE = new ConcurrentHashMap<>(64);

    /**
     * 获取mongo集合实体的主键值
     *
     * @param obj 目标对象
     * @return id对应的值
     */
    public static Serializable getId(Object obj) {

        Field field = getIdField(obj);
        field.setAccessible(true);
        try {
            return (Serializable) field.get(obj);
        } catch (IllegalAccessException e) {
            throw ExceptionUtils.mpe("not exist id value");
        }

    }

    /**
     * 获取mongo集合实体的主键字段
     *
     * @param obj 目标对象
     * @return 主建字段
     */
    public static Field getIdField(Object obj) {

        Class<?> clazz = obj.getClass();
        Field result = FIELD_CACHE.get(clazz);
        if (Objects.nonNull(result)) {
            return result;
        }
        return findIdField(clazz, clazz);
    }

    /**
     * 递归查找主键字段
     *
     * @param originalClass 原始类
     * @param clazz 当前类
     * @return 主键字段
     */
    private static Field findIdField(Class<?> originalClass, Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            Id annotation = field.getAnnotation(Id.class);
            if (Objects.nonNull(annotation)) {
                FIELD_CACHE.put(originalClass, field);
                return field;
            }
        }
        if (clazz.getSuperclass() != null) {
            return findIdField(originalClass, clazz.getSuperclass());
        }
        throw ExceptionUtils.mpe("no exist id");
    }


}
