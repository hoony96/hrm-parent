package cn.itsource.hrm.client;

import cn.itsource.hrm.client.impl.ItemClientFallback;
import cn.itsource.hrm.domain.Systemdictionaryitem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(path = "/systemdictionaryitem",value = "SYSTEM-SERVICE",fallback = ItemClientFallback.class)
public interface SystemdictionaryitemClient {

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Systemdictionaryitem get(@PathVariable("id")Long id);
}
