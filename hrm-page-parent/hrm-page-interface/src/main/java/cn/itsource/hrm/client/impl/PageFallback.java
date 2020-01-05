package cn.itsource.hrm.client.impl;

import cn.itsource.hrm.client.PageClient;
import cn.itsource.hrm.domain.Pager;
import org.springframework.stereotype.Component;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 19:28 2020/1/5
 * @Version : v1.0
 */
@Component
public class PageFallback implements PageClient {

    @Override
    public Pager get(Long id) {
        return new Pager();
    }
}
