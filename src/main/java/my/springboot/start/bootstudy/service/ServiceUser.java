package my.springboot.start.bootstudy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.springboot.start.bootstudy.entity.User;
import my.springboot.start.bootstudy.mapper.UserMapper;

@Service
public class ServiceUser {
    @Autowired
    UserMapper userMapper;

    public User getMine() {
        return userMapper.getMine(1);
    }

    public User insertUser(User user) {
        return userMapper.insertOne(user);
    }

    public User insertByMap(Map<String, Object> userMap) {
        return userMapper.insertOneByMap(userMap);
    }

    public List<User> getUseList() {
        return userMapper.getUserList();
    }
}
