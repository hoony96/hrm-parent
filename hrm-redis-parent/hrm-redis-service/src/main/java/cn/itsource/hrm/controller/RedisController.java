package cn.itsource.hrm.controller;

import cn.itsource.hrm.utils.RedisUtil;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 17:55 2019/12/27
 * @Version : v1.0
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

//    @Autowired
//    private RedisUtil redisUtil;

    @PostMapping("/set")
    public void set(@RequestParam("key") String key, @RequestParam("value") String value){
        RedisUtil.INSTANCE.set(key,value);
    }

    @GetMapping("/get")
    public String get(String key){
        return RedisUtil.INSTANCE.get(key);
    }

}
