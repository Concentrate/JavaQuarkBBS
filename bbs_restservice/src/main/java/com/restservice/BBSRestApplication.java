package com.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by liudeyu on 2019/7/2.
 */

@SpringBootApplication
@EnableCaching
public class BBSRestApplication {

    public static void main(String[] argv) {
        SpringApplication springApplication = new SpringApplication(BBSRestApplication.class);
        InputStream inputStream = BBSRestApplication.class.getClassLoader().getResourceAsStream("rest.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            springApplication.setDefaultProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        springApplication.run(argv);

    }
}
