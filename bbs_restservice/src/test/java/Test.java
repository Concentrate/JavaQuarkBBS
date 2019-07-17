import com.restservice.BBSRestApplication;
import com.restservice.service.RedisService;
import common.dao.UserDao;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by liudeyu on 2019/7/3.
 */
@RunWith(SpringRunner.class)
@EnableCaching
@SpringBootTest(classes = BBSRestApplication.class)
@TestPropertySource(locations = {"classpath:rest.properties"})
public class Test {

    @Autowired
    RedisService<String> redisService;

    @Autowired
    UserDao userDao;

    @Before
    public void beforeConfigure() {
        System.out.println("before configure");
    }

    @org.junit.Test
    public void sayHello() {
        System.out.println("hello");
    }


    @org.junit.Test
    public void saveValue() {
        final String key = "apple";
        redisService.putDataInMap(key, "no way", 1);
        System.out.println(redisService.getDataFromMap(key));
    }
}
