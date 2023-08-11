package com.kai.core.model.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author kai
 */
@Data
public class BaseEntity implements Serializable {


    @Id
    private ObjectId id;
    /**
     * 创建用户
     */
    private String createUser;
    /**
     * 最后修改用户
     */
    private String modifyUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;


}
