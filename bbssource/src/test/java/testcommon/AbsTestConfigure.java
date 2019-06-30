package testcommon;

import common.CommonApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by liudeyu on 2019/6/30.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@EnableCaching
@SpringBootTest(classes = CommonApplication.class)
public abstract  class AbsTestConfigure {
}
