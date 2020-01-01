package cn.itsource.hrm.controller;

import cn.itsource.basic.util.AjaxResult;
import cn.itsource.hrm.doc.ESCourse;
import cn.itsource.hrm.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 16:52 2020/1/1
 * @Version : v1.0
 */
@RestController
@RequestMapping("/es")
public class ESCourseController {

    @Autowired
    private CourseRepository courseRepository;

    /**
     * 创建索引
     */
    @PostMapping("/create")
    public AjaxResult createIndex(@RequestBody List<ESCourse> list){
        try {
            courseRepository.saveAll(list);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("创建失败");
        }
    }


    /**
     * 删除索引
     * @return
     */
    @PostMapping("/delete")
    public AjaxResult deleteIndex(@RequestBody List<Long> ids){
        try {
            for (Long id : ids) {
                courseRepository.deleteById(id);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除失败");
        }

    }

}
