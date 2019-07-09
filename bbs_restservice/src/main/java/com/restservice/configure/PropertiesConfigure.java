package com.restservice.configure;

import org.springframework.boot.env.PropertySourcesLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by liudeyu on 2019/7/4.
 */
@Configuration
@ContextConfiguration(locations = {"classpath:rest.properties"})
public class PropertiesConfigure {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
