package my.springboot.start.bootstudy.service;

import my.springboot.start.bootstudy.entity.User;
import my.springboot.start.bootstudy.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ServiceUserTest {

    @Autowired
    private ServiceUser serviceUser;

    @MockBean
    private UserMapper userMapper;

    @Test
    void testGetMine() {
        // 模拟UserMapper的getMine方法返回值
        User mockUser = new User();
        mockUser.setId(1);
        mockUser.setName("testUser");
        mockUser.setAge(25);
        mockUser.setPassword("testPassword");

        when(userMapper.getMine(anyString(), anyLong())).thenReturn(mockUser);

        // 测试获取用户信息
        User user = serviceUser.getMine();

        // 验证结果
        assertNotNull(user, "用户信息不应为null");
        assertEquals("testUser", user.getName(), "用户名应匹配");
        assertEquals(25, user.getAge(), "年龄应匹配");

        // 验证方法调用
        Mockito.verify(userMapper).getMine("user", 1L);
    }

    @Test
    void testInsertUser() {
        // 创建测试用户
        User user = new User();
        user.setName("testUser");
        user.setAge(25);
        user.setPassword("testPassword");

        // 模拟UserMapper的insert方法返回值
        when(userMapper.insert(any(User.class))).thenReturn(1);

        // 执行插入操作
        int result = serviceUser.insertUser(user);

        // 验证结果
        assertEquals(1, result, "插入操作应返回1表示成功");

        // 验证方法调用
        Mockito.verify(userMapper).insert(user);
    }

    @Test
    void testInsertByMap() {
        // 创建测试数据Map
        Map<String, Object> userMap = Map.of(
                "userName", "mapUser",
                "userAge", 30,
                "userPassword", "mapPassword"
        );

        // 模拟UserMapper的insertOneByMap方法返回值
        when(userMapper.insertOneByMap(userMap)).thenReturn(1);

        // 执行插入操作
        int result = serviceUser.insertByMap(userMap);

        // 验证结果
        assertEquals(1, result, "通过Map插入应返回1表示成功");

        // 验证方法调用
        Mockito.verify(userMapper).insertOneByMap(userMap);
    }


    @Test
    void testInsertUserByMap() {
        // 创建测试用户
        User user = new User();
        user.setName("userByMap");
        user.setAge(35);
        user.setPassword("userByMapPwd");

        // 模拟UserMapper的insertOneByMap方法返回值
        when(userMapper.insertOneByMap(anyMap())).thenReturn(1);

        // 执行插入操作
        int result = serviceUser.insertUserByMap(user);

        // 验证结果
        assertEquals(1, result, "通过User对象转Map插入应返回1表示成功");

        // 验证方法调用
        Mockito.verify(userMapper).insertOneByMap(anyMap());
    }

}