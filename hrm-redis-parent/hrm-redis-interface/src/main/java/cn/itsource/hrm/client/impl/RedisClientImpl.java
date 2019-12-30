package cn.itsource.hrm.client.impl;

import cn.itsource.hrm.client.RedisClient;
import org.springframework.stereotype.Component;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 18:47 2019/12/27
 * @Version : v1.0
 */
@Component
public class RedisClientImpl implements RedisClient {
    @Override
    public void set(String key, String value) {

    }

    @Override
    public String get(String key) {
        return "请求失败,这里是拖地数据";
    }
}
