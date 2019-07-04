import com.restservice.BBSRestApplication;
import org.junit.runner.RunWith;
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

public class Test  {
    
    @org.junit.Test
    public void sayHello(){
        System.out.println("hello");
    }
    
}
