package vip.maosi.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.maosi.redis.service.RedisService;

/**
 * @Author Maosi
 * @Date 2021-08-27 10:17
 * @Describe
 */
@RestController
public class RedisController {

    @Autowired
    RedisService redisService;

    /***
     * @Author 魏鹏 MAOSI-AMOR
     * @Description //使用缓存，保存到redis
     * @Date 11:20 2021-08-27
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("/getStringByCache")
    public String getStringByCache(){
        String stringByCache = redisService.getStringByRedisCache();
        return stringByCache;
    }

    /***
     * @Author 魏鹏 MAOSI-AMOR
     * @Description //使用restTemplate
     * @Date 11:29 2021-08-27
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("/getStringByCacheRestTemplate")
    public String getStringByCacheRestTemplate(){
        String stringByCache = redisService.getStringByRedisTemplateCache();
        return stringByCache;
    }

    /***
     * @Author 魏鹏 MAOSI-AMOR
     * @Description //删除缓存
     * @Date 11:27 2021-08-27
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("/deleteStringByCache")
    public String deleteStringByCache(){
        String stringByCache = redisService.deleteStringByRedisCache();
        return stringByCache;
    }
}
