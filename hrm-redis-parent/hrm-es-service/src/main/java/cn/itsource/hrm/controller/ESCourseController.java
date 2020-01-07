package cn.itsource.hrm.controller;

import cn.itsource.basic.util.AjaxResult;
import cn.itsource.basic.util.PageList;
import cn.itsource.hrm.doc.ESCourse;
import cn.itsource.hrm.query.QueryCourse;
import cn.itsource.hrm.repository.CourseRepository;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

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

    /**
     *  查询上线了的 课程
     * @param queryCourse
     * @return
     */
    @PostMapping("/query")
    PageList<ESCourse> queryCourses(@RequestBody QueryCourse queryCourse){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();

        // 拼接查询条件
        BoolQueryBuilder boolBuilder = new BoolQueryBuilder();
        if(StringUtils.isNotEmpty(queryCourse.getKeyword())){
            boolBuilder.must(QueryBuilders.matchQuery("all", queryCourse.getKeyword()));
        }
        // 拼接过滤条件
        List<QueryBuilder> filter = boolBuilder.filter();
        if(queryCourse.getCourseType() != null){
            filter.add(QueryBuilders.termQuery("courseTypeId",queryCourse.getCourseType()));
        }

        builder.withQuery(boolBuilder);

        // 分页
        builder.withPageable(PageRequest.of(queryCourse.getPage()-1,queryCourse.getRows()));

        // 排序
        SortOrder order = SortOrder.ASC;
        switch (queryCourse.getOrderWay()){
            case 1 : order = SortOrder.ASC;
            break;
            case 2 : order = SortOrder.DESC;
            break;
        }
        String sortField = queryCourse.getSortField();
        builder.withSort(SortBuilders.fieldSort(sortField).order(order));


        Page<ESCourse> courses = courseRepository.search(builder.build());

        return new PageList<>(courses.getTotalElements(),courses.getContent());
    }

}
