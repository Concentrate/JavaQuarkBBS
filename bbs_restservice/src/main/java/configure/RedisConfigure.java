package configure;

import org.springframework.cache.annotation.CachingConfigurationSelector;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liudeyu on 2019/7/2.
 */
@Configuration
@EnableCaching
public class RedisConfigure extends CachingConfigurerSupport{

    
}
