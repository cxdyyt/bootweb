package my.springboot.start.bootstudy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import my.springboot.start.bootstudy.entity.User;
import my.springboot.start.bootstudy.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    ServiceUser serviceUser;

    @GetMapping("/add/{name}")
    public String insertUser(@PathVariable("name") String name, HttpServletRequest request) {
        User user = new User();
        user.setName(name);
        user.setAge(ThreadLocalRandom.current().nextInt(10));
        user.setPassword("pwd");
        int res = serviceUser.insertUser(user);
        return res != 0 ? "success" : "failed";
    }

    @PostMapping("/addByMap")
    public String insertUserByMap(@RequestBody User user, HttpServletRequest request) {
        int res = serviceUser.insertUserByMap(user);
        return res != 0 ? "success" : "failed";
    }

    @RequestMapping("/getUser")
    public User getServiceUser() {
        return serviceUser.getMine();
    }

    @RequestMapping("/deleteByIDs/{ids}")
    public String deleteByIDs(@PathVariable String ids) {
        int res = serviceUser.deleteByIDs(Arrays.stream(ids.split(",")).mapToInt(Integer::parseInt).toArray());
        return res != 0 ? "success" : "failed";
    }
    
    /**
     * 分页查询用户列表
     * @param pageNum 页码，从1开始
     * @param pageSize 每页大小
     * @return 分页结果
     */
    @GetMapping("/page")
    public Page<User> getUserPage(@RequestParam(defaultValue = "1") int pageNum, 
                                 @RequestParam(defaultValue = "10") int pageSize) {
        return serviceUser.getUserPage(pageNum, pageSize);
    }

    /**
     * 带条件的分页查询
     * @param pageNum 页码，从1开始
     * @param pageSize 每页大小
     * @param name 用户名（可选）
     * @param age 年龄（可选）
     * @return 分页结果
     */
    @GetMapping("/page/condition")
    public Page<User> getUserPageWithCondition(@RequestParam(defaultValue = "1") int pageNum,
                                             @RequestParam(defaultValue = "10") int pageSize,
                                             @RequestParam(required = false) String name,
                                             @RequestParam(required = false) Integer age) {
        return serviceUser.getUserPageWithCondition(pageNum, pageSize, name, age);
    }
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return serviceUser.getAllUsers();
    }
}
