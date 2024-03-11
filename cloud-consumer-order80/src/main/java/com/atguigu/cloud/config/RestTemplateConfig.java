package com.atguigu.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @auther zzyy
 * @create 2023-11-04 15:57
 */
@Configuration
public class RestTemplateConfig
{
    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}