package com.kai.core.wrapper;

import com.kai.core.entity.Condition;
import com.kai.core.entity.SelectField;
import com.kai.core.entity.SortCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * 条件包装类
 *
 * @author kai
 * @date 2023/6/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConditionWrapper {

    /**
     * 主动查询的集合字段
     */
    private List<SelectField> fields = Collections.emptyList();

    /**
     * 条件构建器参数集合
     */
    private List<Condition> conditions = Collections.emptyList();

    /**
     * 条件构建器排序集合
     */
    private List<SortCondition> sortConditions = Collections.emptyList();

    /**
     * 分页 skip
     */
    private Long skip;

    /**
     * 分页 limit
     */
    private Integer limit;


}
