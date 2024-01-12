package com.fastfood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = "com.fastfood.mongo.repository")
@EnableTransactionManagement
public class MongoConfig {

    @Bean
    public SimpleMongoDbFactory mongoDbFactory() {
        return new SimpleMongoDbFactory(new MongoClient("localhost", 27017), "fastfoodspringss");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }
}
