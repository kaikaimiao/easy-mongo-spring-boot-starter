package com.kai.core.entity;

import com.kai.core.constant.ESortType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 排序字段
 *
 * @author kai
 * @date 2023/6/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortCondition {

    /**
     * 排序类型
     */
    private ESortType sortType = ESortType.ASC;

    /**
     * 集合对应的列
     */
    private String col;



}
