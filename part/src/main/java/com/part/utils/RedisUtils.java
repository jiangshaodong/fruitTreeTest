package com.part.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisUtils {
    @Resource
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    /***
     * 取出一个商品生成订单
     *
     * @param specialId
     * @return
     */
    public String lPopOrderDetail(String specialId){
        ListOperations<String, String> vo = redisTemplate.opsForList();
        String orderDetail = (String) vo.leftPop(String.format("special_goods%s",specialId));
        return orderDetail;
    }
    /**
     * 获取list缓存的长度
     * @param key 键
     * @return
     */
    public long lGetListSize(String key){
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 写入商品到redis中
     * @param specialId
     * @param content
     */
    public void lPushOrderDetail(String specialId,String content,Integer stock){
        ListOperations<String, String> list = redisTemplate.opsForList();
        for(int i=1;i<=stock;i++){
            list.rightPush(String.format("special_goods%s",specialId),content);
        }
        boolean bool = redisTemplate.expire(String.format("special_goods%s",specialId),1,TimeUnit.HOURS);
        System.out.println(String.format("设置过期时间[%s][%s]",String.format("special_goods%s",specialId),bool));
    }
    /**
     * 删除key为?的list
     * @param specialId
     */
    public void lTrimOrderDetail(String specialId){
        ListOperations<String, String> list = redisTemplate.opsForList();
        list.trim(String.format("special_goods%s",specialId),1,0);
    }

}