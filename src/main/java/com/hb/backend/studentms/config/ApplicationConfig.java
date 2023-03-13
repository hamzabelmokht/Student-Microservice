package com.hb.backend.studentms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Value("${student.endpoint.grades}")
    public String grades;
    @Value("${student.endpoint.account}")
    public String account;
    @Value("${student.endpoint.address}")
    public String address;

    @Value("${student.endpoint.courses}")
    public String courses;

    @Bean
    public RestTemplate restTemplateBean(){
        return new RestTemplate();
    }

}
