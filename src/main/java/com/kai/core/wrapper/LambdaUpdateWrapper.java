package com.kai.core.wrapper;

import cn.hutool.core.collection.CollUtil;
import com.kai.core.enums.ECompare;
import com.kai.core.enums.EConditionType;
import com.kai.core.enums.ESortType;
import com.kai.core.model.Condition;
import com.kai.core.model.SelectField;
import com.kai.core.model.SetCondition;
import com.kai.core.model.SortCondition;
import com.kai.core.wrapper.base.*;
import com.kai.utils.ConvertUtil;
import com.kai.utils.ExceptionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 条件构建器
 *
 * @author kai
 * @date 2023/6/13
 */
public class LambdaUpdateWrapper<T>
        implements
        UpdateSet<LambdaUpdateWrapper<T>, SFunction<T, ?>> {

    private LambdaQueryWrapper<T> lambdaQueryWrapper;


    private List<SetCondition> setConditionList = new ArrayList<>(5);

    /**
     * 构造时候初始lambdaQueryWrapper
     */
    public LambdaUpdateWrapper() {
        lambdaQueryWrapper = new LambdaQueryWrapper<T>();
    }

    @Override
    public LambdaUpdateWrapper<T> set(boolean condition, SFunction<T, ?> column, Object val) {
        if (condition) {
            setConditionList.add(new SetCondition(getFieldMeta(column), val));
        }
        return this;
    }

    private String getFieldMeta(SFunction<T, ?> column) {
        return ConvertUtil.convertToFieldName(column);
    }

    public LambdaQueryWrapper<T> query() {
        return lambdaQueryWrapper;
    }

    public List<SetCondition> getSetConditionList() {
        return setConditionList;
    }
}
