package com.kai.core.wrapper;

import com.kai.core.enums.ECompare;
import com.kai.core.enums.EConditionType;
import com.kai.core.enums.ESortType;
import com.kai.core.model.Condition;
import com.kai.core.model.SelectField;
import com.kai.core.model.SortCondition;
import com.kai.core.wrapper.base.Compare;
import com.kai.core.wrapper.base.Func;
import com.kai.core.wrapper.base.Nested;
import com.kai.core.wrapper.base.SFunction;
import com.kai.utils.ConvertUtil;

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
public class LambdaQueryWrapper<T>
        implements
        Compare<LambdaQueryWrapper<T>, SFunction<T, ?>>,
        Func<T, LambdaQueryWrapper<T>, SFunction<T, ?>>,
        Nested<LambdaQueryWrapper<T>, LambdaQueryWrapper<T>> {

    private final List<SelectField> fields = new ArrayList<>(5);
    private final List<Condition> conditions = new ArrayList<>(5);
    private final List<SortCondition> sortConditions = new ArrayList<>(5);
    private Long skip;
    private Integer limit;

    public ConditionWrapper getCondition() {
        return new ConditionWrapper(fields, conditions, sortConditions, skip, limit);
    }

    private String getFieldMeta(SFunction<T, ?> column) {
        return ConvertUtil.convertToFieldName(column);
    }

    @Override
    public LambdaQueryWrapper<T> eq(boolean condition, SFunction<T, ?> column, Object val) {
        if (condition) {
            conditions.add(new Condition(ECompare.EQ, getFieldMeta(column), Collections.singletonList(val)));
        }
        return this;
    }

    @Override
    public LambdaQueryWrapper<T> eq(boolean condition, Supplier<String> columnSupplier, Object val) {
        if (condition) {
            conditions.add(new Condition(ECompare.EQ, columnSupplier.get(), Collections.singletonList(val)));
        }
        return this;
    }

    @Override
    public LambdaQueryWrapper<T> ne(boolean condition, SFunction<T, ?> column, Object val) {
        if (condition) {
            conditions.add(new Condition(ECompare.NE, getFieldMeta(column), Collections.singletonList(val)));
        }
        return this;
    }

    @Override
    public LambdaQueryWrapper<T> exists(boolean condition, SFunction<T, ?> column, Boolean val) {
        if (condition) {
            conditions.add(new Condition(ECompare.EXISTS, getFieldMeta(column), Collections.singletonList(val)));
        }
        return this;
    }

    @Override
    public LambdaQueryWrapper<T> le(boolean condition, SFunction<T, ?> column, Object val) {
        if (condition) {
            conditions.add(new Condition(ECompare.LE, getFieldMeta(column), Collections.singletonList(val)));
        }
        return this;
    }

    @Override
    public LambdaQueryWrapper<T> lt(boolean condition, SFunction<T, ?> column, Object val) {
        if (condition) {
            conditions.add(new Condition(ECompare.LT, getFieldMeta(column), Collections.singletonList(val)));
        }
        return this;
    }

    @Override
    public LambdaQueryWrapper<T> ge(boolean condition, SFunction<T, ?> column, Object val) {
        if (condition) {
            conditions.add(new Condition(ECompare.GE, getFieldMeta(column), Collections.singletonList(val)));
        }
        return this;
    }

    @Override
    public LambdaQueryWrapper<T> gt(boolean condition, SFunction<T, ?> column, Object val) {
        if (condition) {
            conditions.add(new Condition(ECompare.GT, getFieldMeta(column), Collections.singletonList(val)));
        }
        return this;
    }

    @Override
    public LambdaQueryWrapper<T> between(boolean condition, SFunction<T, ?> column, Object leftV, Object rightV) {
        if (condition) {
            conditions.add(new Condition(ECompare.BW, getFieldMeta(column), Arrays.asList(leftV, rightV)));
        }
        return this;
    }

    @Override
    public LambdaQueryWrapper<T> in(boolean condition, SFunction<T, ?> column, Collection<?> val) {
        if (condition) {
            conditions.add(new Condition(ECompare.IN, getFieldMeta(column), Collections.singletonList(val)));
        }
        return this;
    }

    @Override
    public LambdaQueryWrapper<T> notIn(boolean condition, SFunction<T, ?> column, Collection<?> val) {
        if (condition) {
            conditions.add(new Condition(ECompare.NIN, getFieldMeta(column), Collections.singletonList(val)));
        }
        return this;
    }

    @Override
    public LambdaQueryWrapper<T> regex(boolean condition, SFunction<T, ?> column, String val, String option) {
        if (condition) {
            conditions.add(new Condition(ECompare.REGEX, getFieldMeta(column), Arrays.asList(val, option)));
        }
        return this;
    }

    @Override
    public <N, E> LambdaQueryWrapper<T> setChild(boolean condition, SFunction<N, E> sFunction) {
        if (!condition) {
            return this;
        }
        Condition queryCondition = conditions.get(conditions.size() - 1);
        String col = queryCondition.getCol();
        queryCondition.setCol(col + "." + ConvertUtil.convertToFieldName(sFunction));
        return this;
    }


    @Override
    public LambdaQueryWrapper<T> or(boolean condition, Function<LambdaQueryWrapper<T>, LambdaQueryWrapper<T>> func) {
        if (condition) {
            LambdaQueryWrapper<T> apply = func.apply(new LambdaQueryWrapper<>());
//            if (this.conditions.size() == 0) {
//                throw ExceptionUtils.mpe("not first use and");
//            }
            ConditionWrapper conditionWrapper = apply.getCondition();
            Condition c = new Condition();
            c.setConditionType(EConditionType.OR);
            c.setConditionWrapper(conditionWrapper);
            this.conditions.add(c);
        }
        return this;
    }

    @Override
    public LambdaQueryWrapper<T> and(boolean condition, Function<LambdaQueryWrapper<T>, LambdaQueryWrapper<T>> func) {

        if (condition) {
            LambdaQueryWrapper<T> apply = func.apply(new LambdaQueryWrapper<>());
//            if (this.conditions.size() == 0) {
//                throw ExceptionUtils.mpe("not first use and");
//            }
            ConditionWrapper conditionWrapper = apply.getCondition();
            Condition c = new Condition();
            c.setConditionWrapper(conditionWrapper);
            this.conditions.add(c);
        }
        return this;

    }

//    @Override
//    public LambdaQueryWrapper<T> or() {
//
//        if (CollUtil.isEmpty(conditions)) {
//            throw ExceptionUtils.mpe("not first use or");
//        }
//        Condition lastCondition = conditions.get(conditions.size() - 1);
//        ConditionWrapper conditionWrapper = lastCondition.getConditionWrapper();
//        if (Objects.isNull(conditionWrapper)) {
//            conditionWrapper = new ConditionWrapper(fields, conditions, sortConditions, skip, limit);
//        }
//        List<Condition> sub = conditionWrapper.getConditions();
//        Condition condition = sub.get(sub.size() - 1);
//        condition.setConditionType(EConditionType.OR);
//        return this;
//
//    }

    @Override
    public final LambdaQueryWrapper<T> orderByAsc(boolean condition, SFunction<T, ?> column) {

        if (condition) {
            appendSortField(column, ESortType.ASC);
        }
        return this;

    }

    @Override
    public final LambdaQueryWrapper<T> orderByDesc(boolean condition, SFunction<T, ?> column) {

        if (condition) {
            appendSortField(column, ESortType.DESC);
        }
        return this;

    }

    @Override
    public LambdaQueryWrapper<T> skip(Long skip) {

        this.skip = skip;
        return this;

    }

    @Override
    public LambdaQueryWrapper<T> limit(Integer limit) {

        this.limit = limit;
        return this;

    }

    @SafeVarargs
    @Override
    public final LambdaQueryWrapper<T> select(SFunction<T, ?>... columns) {

        if (Objects.nonNull(columns) && columns.length > 0) {
            List<SelectField> fields = Arrays.stream(columns).map(column -> new SelectField(getFieldMeta(column))).collect(Collectors.toList());
            this.fields.addAll(fields);
        }
        return this;

    }

    private void appendSortField(SFunction<T, ?> column, ESortType sortType) {

        sortConditions.add(new SortCondition(sortType, getFieldMeta(column)));

    }

}
