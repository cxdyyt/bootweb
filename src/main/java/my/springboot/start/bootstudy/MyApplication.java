package my.springboot.start.bootstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

// 使用Spring Boot自动扫描Mapper，或者使用
@RestController
@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients
public class MyApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(MyApplication.class, args);
    }

}