package my.springboot.start.bootstudy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import my.springboot.start.bootstudy.entity.User;
import my.springboot.start.bootstudy.service.ServiceUser;

@SpringBootTest
public class ServiceUserTest {
    @Autowired
    ServiceUser serviceUser;

    @Test
    public void test1() {
        User user = serviceUser.getMine();
        System.out.println(user);
    }
}
