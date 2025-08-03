package my.springboot.start.bootstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;

import ch.qos.logback.classic.db.names.TableName;
import my.springboot.start.bootstudy.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

// MyBatis-Plus会自动扫描，也可以保留@Mapper注解
@Repository
public interface UserMapper extends BaseMapper<User> {
    public User getMine( @Param("tbname") String tableName, long id);

    int insertOne(User user);

    public int insertOneByMap(Map userMap);

    public List<User> getUserList();
}
