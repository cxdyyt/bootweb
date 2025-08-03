package my.springboot.start.bootstudy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import my.springboot.start.bootstudy.entity.User;

@Mapper
public interface UserMapper {
    public User getMine(long idd);

    public User insertOne(User user);

    public User insertOneByMap(Map userMap);

    public List<User> getUserList();
}
