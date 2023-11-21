package com.kai.core.model;

import com.kai.core.enums.ECompare;
import com.kai.core.enums.EConditionType;
import com.kai.core.wrapper.ConditionWrapper;
import lombok.Data;

import java.util.List;

/**
 * update set构建类
 *
 * @author kai
 * @date 2023/6/13
 */

@Data
public class SetCondition {

    public SetCondition() {
    }

    public SetCondition(String col, Object args) {
        this.col = col;
        this.args = args;
    }


    /**
     * 集合对应的列
     */
    private String col;

    /**
     * set的值
     */
    private Object args;


}
