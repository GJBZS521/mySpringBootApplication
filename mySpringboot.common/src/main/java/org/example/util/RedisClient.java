package org.example.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Configuration
@Component("redisClient")
public class RedisClient {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password")
    private String password;

    @Value("${spring.redis.port}")
    private int port;

//    @Autowired
//    private RedissonClient redissonClient;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void run() {
//        Jedis jedis = new Jedis(host, port);
//
//        String str = jedis.get("mykey1");
//
//        System.out.println(str);
    }

    public String getValueByKey(String key) {

        String result = stringRedisTemplate.opsForValue().get(key);
        if (result == null) {
            return "not found result by key";
        }
        return result;
    }
}
