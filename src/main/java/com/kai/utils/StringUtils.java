package com.kai.utils;

import cn.hutool.core.util.ArrayUtil;
import lombok.experimental.UtilityClass;

/**
 * 字符串工具类
 *
 * @author kai
 * @date 2023/6/13
 */
@UtilityClass
public class StringUtils {


    /**
     * 安全的进行字符串 format
     *
     * @param target 目标字符串
     * @param params format 参数
     * @return format 后的
     */
    public static String format(String target, Object... params) {
        if (target.contains("%s") && ArrayUtil.isNotEmpty(params)) {
            return String.format(target, params);
        }
        return target;
    }

    /**
     * 首字母转换小写
     *
     * @param str 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String firstToLowerCase(final String str) {
        if (null == str || str.length() == 0) {
            return "";
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }
}
