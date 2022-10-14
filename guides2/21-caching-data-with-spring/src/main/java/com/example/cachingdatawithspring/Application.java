package com.example.cachingdatawithspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @EnableCaching: triggers a post-processor that inspects every Spring bean
 * for the presence of caching annotations on public methods. If such an annotation
 * is found, a proxy is automatically created to intercept the method call
 * and handle the caching behavior accordingly.
 *
 * The post-processor handles the @Cacheable, @CachePut, and @CacheEvict annotations.
 * Spring Boot automatically configures a suitable 'CacheManager' to sever as a provider
 * for the relevant cache. for more details please refer to Spring Boot documentation.
 */
@SpringBootApplication
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
