package testcommon;

import common.dao.UserDao;
import common.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
