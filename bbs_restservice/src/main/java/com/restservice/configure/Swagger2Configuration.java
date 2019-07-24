package com.restservice.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by liudeyu on 2019/7/24.
 */

@Configuration
@EnableSwagger2
public class Swagger2Configuration {


    @Bean
    public Docket provideDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(provideInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.restservice.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo provideInfo() {
        return new ApiInfoBuilder()
                .title("my rest service doc")
                .version("1.0")
                .contact("1098014590@qq.com")
                .build();
    }
}
