package com.kai.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询的列
 *
 * @author kai
 * @date 2023/6/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectField {

    /**
     * 集合对应的列
     */
    private String col;


}
