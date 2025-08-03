package my.springboot.start.bootstudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisClusterService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 存储键值对
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 存储键值对并设置过期时间
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    // 获取值
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 删除键
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    // 操作 Hash 类型数据
    public void putHash(String hashKey, String field, Object value) {
        redisTemplate.opsForHash().put(hashKey, field, value);
    }

    // 获取 Hash 类型数据
    public Object getHash(String hashKey, String field) {
        return redisTemplate.opsForHash().get(hashKey, field);
    }

    // 其他操作...
}
