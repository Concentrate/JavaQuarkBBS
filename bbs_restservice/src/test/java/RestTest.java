import com.restservice.BBSRestApplication;
import com.restservice.service.PostService;
import com.restservice.service.RedisService;
import com.restservice.service.UserService;
import common.dao.UserDao;
import common.entity.Posts;
import common.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by liudeyu on 2019/7/3.
 */
@RunWith(SpringRunner.class)
@EnableCaching
@SpringBootTest(classes = BBSRestApplication.class)
@TestPropertySource(locations = {"classpath:rest.properties"})
public class RestTest {

    @Autowired
    RedisService<String> redisService;

    @Autowired
    UserDao userDao;

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger("RestTest");

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
        System.out.println("apple key and the value is " + redisService.getDataFromMap(key));
    }

    @Test
    public void testPostService() {
        List<User> users = userService.findAll();
        logger.info("start to log");
        logger.warn("warn color");
        if (users != null && !users.isEmpty()) {
            users.stream().findFirst()
                    .ifPresent(user -> {
                        List<Posts> posts=postService.getPostsByUser(user);
                        posts.stream().forEach(posts1 -> {
                            logger.info(posts1.toString());
                        });
                    });
        }
    }



}
