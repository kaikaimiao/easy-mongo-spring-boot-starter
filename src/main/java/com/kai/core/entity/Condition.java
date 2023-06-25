package com.kai.core.entity;

import com.kai.core.constant.ECompare;
import com.kai.core.constant.EConditionType;
import com.kai.core.wrapper.ConditionWrapper;

import java.util.List;

/**
 * 条件
 *
 * @author kai
 * @date 2023/6/13
 */
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

    public EConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(EConditionType conditionType) {
        this.conditionType = conditionType;
    }

    public ECompare getType() {
        return type;
    }

    public void setType(ECompare type) {
        this.type = type;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public List<Object> getArgs() {
        return args;
    }

    public void setArgs(List<Object> args) {
        this.args = args;
    }

    public ConditionWrapper getConditionWrapper() {
        return conditionWrapper;
    }

    public void setConditionWrapper(ConditionWrapper conditionWrapper) {
        this.conditionWrapper = conditionWrapper;
    }
}
