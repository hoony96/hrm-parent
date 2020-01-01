package cn.itsource.hrm.client.impl;

import cn.itsource.basic.util.AjaxResult;
import cn.itsource.hrm.client.ESCourseClient;
import cn.itsource.hrm.doc.ESCourse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 17:11 2020/1/1
 * @Version : v1.0
 */
@Component
public class ESCourseFallback implements ESCourseClient {
    @Override
    public AjaxResult createIndex(List<ESCourse> list) {
        return AjaxResult.me().setSuccess(false).setMessage("失败,请重试");
    }

    @Override
    public AjaxResult deleteIndex(List<Long> ids) {
        return AjaxResult.me().setSuccess(false).setMessage("失败,请重试");
    }
}
