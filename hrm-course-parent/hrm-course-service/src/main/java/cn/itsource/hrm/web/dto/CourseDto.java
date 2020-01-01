package cn.itsource.hrm.web.dto;

import cn.itsource.hrm.domain.CourseType;
import lombok.Data;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 19:49 2020/1/1
 * @Version : v1.0
 */
@Data
public class CourseDto {
    private Long id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 适用人群
     */
    private String users;

    /**
     * 课程大分类
     */
    private Long courseTypeId;

    private CourseType courseType;

    /**
     * 课程等级
     */
    private Long grade;

    /**
     * 课程状态  0 未上架  1 上架
     */
    private Integer status = 0;

    /**
     * 教育机构
     */
    private Long tenantId = 39L;

    private String tenantName = "哔哩哔哩视频";

    /**
     * 创建用户
     */
    private Long userId = 1L;

    private String userName = "hoony";

    private Long startTime = System.currentTimeMillis();

    private Long endTime = this.startTime + 111111L;



    /**
     * 详情
     */
    private String description;

    /**
     * 简介
     */
    private String intro;

}
