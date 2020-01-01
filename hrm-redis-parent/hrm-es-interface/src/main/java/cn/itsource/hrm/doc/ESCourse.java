package cn.itsource.hrm.doc;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 16:46 2020/1/1
 * @Version : v1.0
 */
@Data
@Document(indexName = "hrm",type = "course")
public class ESCourse {
    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer ="ik_max_word")
    private String users;
    @Field(type = FieldType.Long)
    private Long courseTypeId;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer ="ik_max_word")
    private String courseTypeName;
    @Field(type = FieldType.Long)
    private Long gradeId;
    @Field(type = FieldType.Keyword)
    private String gradeName;
    @Field(type = FieldType.Integer)
    private Integer status;
    @Field(type = FieldType.Long)
    private Long tenantId;

    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer ="ik_max_word")
    private String tenantName;
    @Field(type = FieldType.Long)
    private Long userId;
    @Field(type = FieldType.Keyword)
    private String userName;
    @Field(type = FieldType.Long)
    private Long startTime;
    @Field(type = FieldType.Long)
    private Long endTime;

    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer ="ik_max_word")
    private String intro;
    @Field(type = FieldType.Keyword)
    private String resources; //图片
    @Field(type = FieldType.Long)
    private Long expires; //过期时间
    @Field(type = FieldType.Double)
    private BigDecimal priceOld; // 原价
    @Field(type = FieldType.Double)
    private BigDecimal price; // 售价
    @Field(type = FieldType.Keyword)
    private String qq; // 联系方式

    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String all;
}
