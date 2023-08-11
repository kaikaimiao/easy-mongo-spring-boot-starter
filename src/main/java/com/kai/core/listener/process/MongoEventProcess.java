package com.kai.core.listener.process;

import com.kai.core.model.entity.BaseEntity;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;

/**
 * @author kai
 * @version 1.0
 * @className MongoEventProcess
 * @description mongo事件处理接口
 * @date 2023/8/11 10:31
 */
public interface MongoEventProcess {
    /**
     * 处理前事件
     *
     * @param event 插入前事件处理
     */
    void beforeSaveEvent(BeforeSaveEvent<BaseEntity> event);

    /**
     * 删除
     *
     * @param event 删除前事件
     */
    void beforeDeleteEvent(BeforeDeleteEvent<BaseEntity> event);

    /**
     * 转换事件
     *
     * @param event 转换事件
     */
    void beforeConvertEvent(BeforeConvertEvent<BaseEntity> event);
}
