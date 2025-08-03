package my.springboot.start.bootstudy.service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    // 使用分布式锁的示例方法
    public void lockedOperation(String lockKey, String dataKey, Object data) {
        RLock lock = redissonClient.getLock(lockKey);

        try {
            // 尝试获取锁，最多等待10秒，锁持有时间30秒
//            boolean isLocked = lock.tryLock(10, 30, TimeUnit.SECONDS);
            lock.lock();
//            if (isLocked) {
                try {
                    // 在锁保护下执行操作
                    redisTemplate.opsForValue().set(dataKey, data);
                    redisTemplate.opsForHash().put("fruit","name","apple");
                    redisTemplate.opsForHash().put("fruit","color","red");
                    redisTemplate.opsForHash().put("fruit","name","orange");
                    redisTemplate.opsForHash().put("fruit","color","yellow");
                    // 模拟一些业务操作
                    System.out.println("process is finished");
                } finally {
                    // 释放锁
                    lock.unlock();
                }
//            } else {
//                // 获取锁失败处理
//                System.out.println("获取锁失败");
//            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    // 普通Redis操作示例
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
