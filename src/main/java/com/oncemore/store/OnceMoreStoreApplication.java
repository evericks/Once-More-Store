package com.oncemore.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OnceMoreStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnceMoreStoreApplication.class, args);
    }
}
