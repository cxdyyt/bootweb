package my.springboot.start.bootstudy.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.springboot.start.bootstudy.entity.User;
import my.springboot.start.bootstudy.mapper.UserMapper;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceUser {
    @Autowired
    UserMapper userMapper;

    public User getMine() {
        return userMapper.getMine("user",1);
    }

    @Transactional
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    public int insertByMap(Map<String, Object> userMap) {
        return userMapper.insertOneByMap(userMap);
    }

    public List<User> getUseList() {
//        QueryWrapper<User> query = new QueryWrapper<>();
//        query.gt("age", 1)       // 年龄 > 18
//                .like("name", "bao") // 用户名 LIKE '%张%'
//                .orderByDesc("name"); // 按创建时间降序
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        String name = "bao";
        query.select(User::getId, User::getName);
        query.gt(User::getAge, 1)
                .like(StringUtils.isNotEmpty(name),User::getName, name)
                .orderByDesc(User::getName);
        return userMapper.selectList(query);
    }

    public int insertUserByMap(User user) {
        return userMapper.insertOneByMap(Map.of("name", user.getName(), "age", user.getAge(), "password", user.getPassword()));
    }
    public int deleteByIDs(int[] ids){
        return userMapper.deleteBatchIds(Arrays.stream(ids).boxed().toList());
    }
    
    // 分页查询用户列表
    public Page<User> getUserPage(int pageNum, int pageSize) {
        // 创建分页对象
        Page<User> page = new Page<>(pageNum, pageSize);
        // 使用MyBatis-Plus的分页查询
        return userMapper.selectPage(page, null);
    }
    
    // 带条件的分页查询
    public Page<User> getUserPageWithCondition(int pageNum, int pageSize, String name, Integer age) {
        Page<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        
        // 添加查询条件
        if (name != null && !name.isEmpty()) {
            queryWrapper.like("name", name);
        }
        if (age != null) {
            queryWrapper.eq("age", age);
        }
        
        return userMapper.selectPage(page, queryWrapper);
    }
    public int updateUsers(){
//        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.ge("age", 2).eq("name", "baobao")
//                .set("password", "newpassword");
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(User::getPassword, "anotherpassword")
                .set(User::getAge, 5);
        lambdaUpdateWrapper.ge(User::getAge, 2).eq(User::getName, "baobao");
        return userMapper.update(lambdaUpdateWrapper);
    }

    public List<User> getAllUsers() {
        return userMapper.selectList(null);
    }
}
