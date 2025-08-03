package my.springboot.start.bootstudy.controller;

import my.springboot.start.bootstudy.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@ResponseBody
public class JavaServicePagesTest {

    private static final String datePattern = "yyyy-MM-dd E HH:mm:ss";
//    @Value("${name}")
    private String name;
    @Autowired
    BusinessService businessService;
    @RequestMapping("/test")
    public String methodOne(HttpServletRequest request) {
        System.out.println("nacos：" + name);
        request.setAttribute("time", new SimpleDateFormat(datePattern).format(new Date()));
        businessService.doBusinessOperation();
        return "bootweb test";
    }
}
