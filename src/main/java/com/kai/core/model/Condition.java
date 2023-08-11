package com.kai.core.model;

import com.kai.core.enums.ECompare;
import com.kai.core.enums.EConditionType;
import com.kai.core.wrapper.ConditionWrapper;
import lombok.Data;

import java.util.List;

/**
 * 条件
 *
 * @author kai
 * @date 2023/6/13
 */

@Data
public class Condition {

    public Condition() {
    }

    public Condition(ECompare type, String col, List<Object> args) {
        this.type = type;
        this.col = col;
        this.args = args;
    }

    /**
     * 嵌套条件比较类型
     */
    private EConditionType conditionType = EConditionType.AND;

    /**
     * 比较类型
     */
    private ECompare type;

    /**
     * 集合对应的列
     */
    private String col;

    /**
     * 比较值
     */
    private List<Object> args;

    /**
     * 条件包装类
     */
    private ConditionWrapper conditionWrapper;

}
