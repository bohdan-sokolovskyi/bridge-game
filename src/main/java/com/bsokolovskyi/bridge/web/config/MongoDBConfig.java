package com.bsokolovskyi.bridge.web.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = { "com.bsokolovskyi.bridge.web.db.repository" })
@Configuration
public class MongoDBConfig {

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private String port;

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Bean
    public MongoClient mongo() {
        return MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(String.format("mongodb://%s:%s/", host, port)))
                .build());
    }

    @Bean
    public MongoTemplate mongoTemplate(@Autowired MongoClient mongo) {
        return new MongoTemplate(mongo, dbName);
    }
}
