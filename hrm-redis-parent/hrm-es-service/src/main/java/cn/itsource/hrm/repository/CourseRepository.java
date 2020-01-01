package cn.itsource.hrm.repository;

import cn.itsource.hrm.doc.ESCourse;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CourseRepository extends ElasticsearchRepository<ESCourse,Long> {
}
