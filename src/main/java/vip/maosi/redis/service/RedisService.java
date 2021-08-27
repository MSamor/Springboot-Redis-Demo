package vip.maosi.redis.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * @Author Maosi
 * @Date 2021-08-27 10:17
 * @Describe
 */
@Service
public class RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    /***
     * @Author 魏鹏 MAOSI-AMOR
     * @Description //模拟查询数据库放入缓存，只要配置文件不配置redis，就会保存到spring中的缓存中
     * @Date 11:42 2021-08-27
     * @Param []
     * @return java.lang.String
     **/
    @Cacheable(cacheNames="localCache",key = "'localKey'")
    public String getStringByLocalCache(){
        // todo 查询数据库
        System.out.println("进入缓存方法1");
        return "缓存方法返回值1";
    }

    /***
     * @Author 魏鹏 MAOSI-AMOR
     * @Description //模拟查询数据库放入缓存
     * @Date 11:42 2021-08-27
     * @Param []
     * @return java.lang.String
     **/
    @Cacheable(cacheNames="redisCache",key = "'redisKey'")
    public String getStringByRedisCache(){
        // todo 查询数据库
        System.out.println("进入缓存方法2");
        return "缓存方法返回值2";
    }

    /***
     * @Author 魏鹏 MAOSI-AMOR
     * @Description //带不定参数
     * @Date 11:44 2021-08-27
     * @Param []
     * @return java.lang.String
     **/
    @Cacheable(cacheNames="redisCache",key = "'redisKey'+#id")
    public String getStringByRedisCacheParam(int id){
        // todo 查询数据库
        System.out.println("进入缓存方法2");
        //会根据id的不同生成不同的key，存入redis
        return "缓存方法返回值2";
    }

    /***
     * @Author 魏鹏 MAOSI-AMOR
     * @Description //使用restTemplate操作缓存
     * @Date 11:42 2021-08-27
     * @Param []
     * @return java.lang.String
     **/
    public String getStringByRedisTemplateCache(){

        //设置数据
        redisTemplate.opsForValue().set("key","缓存方法返回值3", Duration.ofMinutes(30));

        //获取数据
        Long key = redisTemplate.getExpire("key");
        System.out.println("剩余时间："+ key);
        System.out.println("进入缓存方法3");
        return "缓存方法返回值3";
    }

    /***
     * @Author 魏鹏 MAOSI-AMOR
     * @Description //清除缓存
     * @Date 11:41 2021-08-27
     * @Param []
     * @return java.lang.String
     **/
    @CacheEvict(cacheNames = {"localCache","redisCache"},allEntries = true)
    public String deleteStringByRedisCache(){
        System.out.println("进入缓存方法4");
        return "缓存方法返回值4";
    }
}
