package com.sparta.miniproject0212.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // 서버의 자원에 접근할 출처를 명시
                .allowedMethods("*") // 요청이 허용되는 메소드 지정
                .allowedHeaders("*") // 서버로 요청 시 사용할 수 있는 헤더 명시
                .exposedHeaders(HttpHeaders.AUTHORIZATION); // 브라우저에 표시할 헤더명 표시
                //.allowCredentials(true); //클라이언트 쿠키 받는거 허용



    }


}
