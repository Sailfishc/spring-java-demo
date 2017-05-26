package com.sailfish.config;

import com.sailfish.retry.Person;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sailfish
 * @create 2017-05-26-下午9:52
 */
@Configuration
public class RetryConfig {

    @Bean
    public Person person(){
        return new Person();
    }
}
