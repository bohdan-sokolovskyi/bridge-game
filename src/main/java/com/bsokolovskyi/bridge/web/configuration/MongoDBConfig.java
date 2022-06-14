package com.bsokolovskyi.bridge.web.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoDBConfig {

    @Bean
    public MongoClient mongo() {
        return MongoClients.create(
                MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString("mongodb://localhost:27017/users"))
                        .build()
        );
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), "users");
    }
}
