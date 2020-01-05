package cn.itsource.hrm.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "STATIC-PAGE")
public interface StaticPageClient {

    @PostMapping("/staticPageInSP")
    void staticPageInSP(@RequestParam("key") String key, @RequestParam("pageId") Long pageId);
}
