package cn.itsource.hrm.client;

import cn.itsource.hrm.client.impl.RedisClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 18:43 2019/12/27
 * @Version : v1.0
 */
// value 指的是 对应注册服务名
@FeignClient(path = "/redis",value = "REDIS-SERVICE",fallback = RedisClientImpl.class)
public interface RedisClient {


    @PostMapping("/set")
    public void set(@RequestParam("key") String key, @RequestParam("value") String value);

    @GetMapping("/get")
    public String get(@RequestParam("key") String key);
}
