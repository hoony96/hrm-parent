package cn.itsource.hrm.client;

import cn.itsource.hrm.client.impl.PageFallback;
import cn.itsource.hrm.domain.Pager;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 19:27 2020/1/5
 * @Version : v1.0
 */
@FeignClient(path = "/pager",value = "PAGE-SERVICE",fallback = PageFallback.class)
public interface PageClient {

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    Pager get(@PathVariable("id")Long id);

}
