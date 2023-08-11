package com.kai.core.model;

import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * 分页数据
 *
 * @author kai
 * @date 2023/6/13
 */
@Data
public class Page<T> {

    /**
     * 查询数据列表
     */
    private List<T> content = Collections.emptyList();

    /**
     * 总数
     */
    private long total = 0;

    /**
     * 每页显示条数，默认 10
     */
    private int pageSize = 10;

    /**
     * 当前页
     */
    private int pageNum = 1;

    /**
     * 转换方法
     *
     * @param mapper 转换器
     * @param <R>    转换类型
     * @return 分页对象
     */
    public <R> Page<R> convert(Function<? super T, ? extends R> mapper) {
        List<R> collect = this.getContent().stream().map(mapper).collect(toList());
        ((Page<R>) this).setContent(collect);
        return (Page<R>) this;
    }

    /**
     * 总页数
     */
    private int totalPages = 0;
}
