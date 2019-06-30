package common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liudeyu on 2019/6/30.
 */

@SpringBootApplication
@EnableCaching
public class CommonApplication {
    public static void main(String[]argv){
        SpringApplication.run(CommonApplication.class,argv);
    }
}
