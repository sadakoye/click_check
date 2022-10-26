package com.check.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Resource
    private RedisTemplate redisTemplate;


    private static RedisUtils redisUtils ;

    @PostConstruct
    public void init(){
        redisUtils = this ;
        redisUtils.redisTemplate = this.redisTemplate ;
    }

    /**
     * redis存入数据
     * @param key 键名
     * @param value  值
     * @param time 保存时间
     * @param timeUnit  时间单位
     * */
    public static void saveValue(String key, Object value, int time, TimeUnit timeUnit){
        redisUtils.redisTemplate.opsForValue().set(key,value,time,timeUnit);
    }

    /**
     * 获取redis中的值
     * @param key 键名
     * */
    public static <T> T getValue(String key){
        ValueOperations<String,T> valueOperations = redisUtils.redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    /**
     * 删除单个对象
     * @param key 键名
     * */
    public static  boolean deleteValue(String key){
        return  redisUtils.redisTemplate.delete(key);
    }
}