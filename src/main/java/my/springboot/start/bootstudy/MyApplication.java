package my.springboot.start.bootstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MyApplication {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(MyApplication.class, args);
        String name = context.getEnvironment().getProperty("name");
        System.out.println("=================== "+name);
    }

}