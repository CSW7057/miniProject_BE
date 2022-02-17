package com.sparta.miniproject0212;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ServletComponentScan("lecturer")
@EnableJpaAuditing
@SpringBootApplication
public class ChoiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChoiApplication.class, args);
    }

}
