package cn.itsource.hrm.client.impl;

import cn.itsource.hrm.client.SystemdictionaryitemClient;
import cn.itsource.hrm.domain.Systemdictionaryitem;
import org.springframework.stereotype.Component;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 17:44 2020/1/1
 * @Version : v1.0
 */
@Component
public class ItemClientFallback implements SystemdictionaryitemClient {


    @Override
    public Systemdictionaryitem get(Long id) {
        return null;
    }
}
