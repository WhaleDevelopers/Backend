package com.kim.devstu.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ConnectionPoolSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

public class MongoConfig {

    @Value("${mongodb.uri}")
    private String mongoUri;

    @Value("${mongodb.username}")
    private String username;

    @Value("${mongodb.password}")
    private String password;


    @Bean
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(mongoUri);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .credential(MongoCredential.createCredential(username, "admin", password.toCharArray()))
                .applyToConnectionPoolSettings(builder ->
                        builder.maxSize(20)
                                .minSize(5)
                                .maxWaitTime(2, TimeUnit.MINUTES)   //스레드가 풀에서 커넥션을 기다릴 수 있는 최대 시간
                                .maxConnectionIdleTime(30000, TimeUnit.MILLISECONDS)    //연결이 사용되지 않고 유휴 상태로 있을 수 있는 최대 시간
                                .maxConnectionLifeTime(1800000, TimeUnit.MILLISECONDS)) //연결의 총 수명 시간 (사용 여부와 관계없이)
                .build();
        return MongoClients.create(settings);
    }

}
