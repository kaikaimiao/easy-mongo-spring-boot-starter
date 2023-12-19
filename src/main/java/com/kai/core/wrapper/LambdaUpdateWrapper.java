package com.kai.core.wrapper;

import com.kai.core.model.SetCondition;
import com.kai.core.wrapper.base.SFunction;
import com.kai.core.wrapper.base.UpdateSet;
import com.kai.utils.ConvertUtil;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public <N, E> LambdaUpdateWrapper<T> setChild(boolean condition, SFunction<N, E> sFunction) {
        if (!condition) {
            return this;
        }
        SetCondition setCondition = setConditionList.get(setConditionList.size() - 1);
        String col = setCondition.getCol();
        setCondition.setCol(col + "." + ConvertUtil.convertToFieldName(sFunction));
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
