package cn.itsource.hrm.query;

import lombok.Data;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 15:18 2020/1/7
 * @Version : v1.0
 */
@Data
public class QueryCourse {

    private Integer page = 1;
    private Integer rows = 20;

    private String keyword;

    private Long courseType; // 课程类型  上线后 页面查询用

    private String sortField= "id";

    private Integer orderWay = 1;
}
