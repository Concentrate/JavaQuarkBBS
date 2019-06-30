package testcommon;

import common.CommonApplication;
import common.dao.UserDao;
import common.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

public class TestOne extends AbsTestConfigure{
    
    @Autowired
    UserDao userDao;
    @Test
    public void testHello(){
        System.out.println("hello");
    }
    
    
    @Test
    public void testDataBase(){
        List<User>newUsers=userDao.findNewUser();
        for(User u:newUsers){
            System.out.println(u.getUsername());
        }
    }
    
}
