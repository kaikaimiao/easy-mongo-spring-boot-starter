package com.kai.core.event;

import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

/**
 * @author: kai
 * @since: 2023/6/25 13:46
 * @description: mongo event消费
 */
public class MongoEventListener {

    @EventListener
    public void beforeConvertEvent(BeforeConvertEvent event) {
    }

}
