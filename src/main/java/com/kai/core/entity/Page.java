package com.kai.core.entity;

import lombok.Data;

import java.util.Collections;
import java.util.List;

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
    private List<T> records = Collections.emptyList();

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




}
