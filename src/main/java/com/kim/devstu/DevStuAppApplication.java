package com.kim.devstu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableCaching	// Redis Cache
@EnableRedisRepositories // Redis Repository 기능 활성화
@SpringBootApplication
public class DevStuAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevStuAppApplication.class, args);
	}

}
