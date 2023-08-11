package com.kai.core.listener;

import com.kai.core.listener.process.MongoEventProcess;
import com.kai.core.model.entity.BaseEntity;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;

import java.util.List;

/**
 * @author: kai
 * @since: 2023/6/25 13:46
 * @description: mongo event消费
 */
public class MongoEventListener extends AbstractMongoEventListener<BaseEntity> {

    private List<MongoEventProcess> mongoEventProcesses;

    public MongoEventListener(List<MongoEventProcess> list) {
        this.mongoEventProcesses = list;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<BaseEntity> event) {
        for (MongoEventProcess mongoEventProcess : mongoEventProcesses) {
            mongoEventProcess.beforeConvertEvent(event);
        }
    }

    @Override
    public void onBeforeSave(BeforeSaveEvent<BaseEntity> event) {
        for (MongoEventProcess mongoEventProcess : mongoEventProcesses) {
            mongoEventProcess.beforeSaveEvent(event);
        }
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<BaseEntity> event) {
        for (MongoEventProcess mongoEventProcess : mongoEventProcesses) {
            mongoEventProcess.beforeDeleteEvent(event);
        }
    }

}
