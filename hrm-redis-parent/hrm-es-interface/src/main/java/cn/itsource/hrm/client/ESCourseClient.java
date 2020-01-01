package cn.itsource.hrm.client;

import cn.itsource.basic.util.AjaxResult;
import cn.itsource.hrm.client.impl.ESCourseFallback;
import cn.itsource.hrm.doc.ESCourse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 17:09 2020/1/1
 * @Version : v1.0
 */
@FeignClient(path = "/es",value = "ES-SERVICE",fallback = ESCourseFallback.class)
public interface ESCourseClient {

    @PostMapping("/create")
    public AjaxResult createIndex(@RequestBody List<ESCourse> list);

    @PostMapping("/delete")
    public AjaxResult deleteIndex(@RequestBody List<Long> ids);

}
