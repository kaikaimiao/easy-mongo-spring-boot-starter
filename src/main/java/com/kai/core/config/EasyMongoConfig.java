package com.kai.core.config;

import com.kai.core.listener.MongoEventListener;
import com.kai.core.listener.process.MongoEventProcess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author kai
 * @version 1.0
 * @className EasyMongoConfig
 * @description 配置类
 * @date 2023/8/11 10:37
 */
@Configuration
public class EasyMongoConfig {

    @Bean
    public MongoEventListener mongoEventListener(List<MongoEventProcess> list) {
        return new MongoEventListener(list);
    }
}
