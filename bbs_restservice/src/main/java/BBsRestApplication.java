import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Created by liudeyu on 2019/7/2.
 */

@SpringBootApplication
@EnableCaching
public class BBsRestApplication {

    public static void main(String[] argv) {
        SpringApplication.run(
                BBsRestApplication.class, argv);
    }
}
