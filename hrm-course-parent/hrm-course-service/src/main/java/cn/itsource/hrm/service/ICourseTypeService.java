package cn.itsource.hrm.service;

import cn.itsource.hrm.domain.CourseType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程目录 服务类
 * </p>
 *
 * @author hoony96
 * @since 2019-12-25
 */
public interface ICourseTypeService extends IService<CourseType> {

    List<CourseType> loadTreeData();

    void prepareDataAndPage(Long pageId);

    List<Map<String,Object>> getCrumbs(Long courseTypeId);

//    List<CourseType> loadLastChilds();
}
