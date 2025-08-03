package my.springboot.start.bootstudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {
    {
    ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    threadLocal.set(234);
    }
    @Autowired
    private RedisClusterService redisService;

    public void doBusinessOperation() {

        String lockKey = "myLock";
        String dataKey = "myData";
        String data = "Hello Redis with Redisson Lock!";


        // 直接使用RedisTemplate
        redisService.set("cluster", "This is cluster value");
        System.out.println(redisService.get("cluster"));
    }
}
