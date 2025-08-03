package my.springboot.start.bootstudy.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import my.springboot.start.bootstudy.service.BusinessService;
import my.springboot.start.bootstudy.service.ServiceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@ResponseBody
public class JavaServicePagesTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String datePattern = "yyyy-MM-dd E HH:mm:ss";
    @Autowired
    BusinessService businessService;
    @RequestMapping("/test")
    public String methodOne(HttpServletRequest request) {
        request.setAttribute("time", new SimpleDateFormat(datePattern).format(new Date()));
        businessService.doBusinessOperation();
        return "bootweb test";
    }

    @RequestMapping("/testnacos")
    public String testnacos(HttpServletRequest request) {
        request.setAttribute("time", new SimpleDateFormat(datePattern).format(new Date()));
        businessService.doBusinessOperation();
        return "nacos test";
    }

    @RequestMapping("/hottest/{id}")
    @SentinelResource(value = "hotTest", blockHandler = "HotBlockHandler")
    public String hotTest(@PathVariable("id") Integer id, HttpServletRequest request) {
        logger.info("id: {} ", id);
        request.setAttribute("time", new SimpleDateFormat(datePattern).format(new Date()));
        businessService.doBusinessOperation();
        return "bootweb hottest";
    }

    public String HotBlockHandler(@PathVariable("id") Integer id, HttpServletRequest request, BlockException e) {
        return "hot point process";
    }
}
