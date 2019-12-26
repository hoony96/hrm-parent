package cn.itsource.hrm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 课程目录
 * </p>
 *
 * @author hoony96
 * @since 2019-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_course_type")
public class CourseType extends Model<CourseType> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("createTime")
    private Long createTime = new Date().getTime();

    @TableField("updateTime")
    private Long updateTime = new Date().getTime();

    /**
     * 类型名
     */
    private String name;

    /**
     * 父ID  == > 下拉框选择
     */
    private Long pid;

    /**
     * 图标  ==> 图片上传?
     */
    private String logo;

    /**
     * 描述
     */
    private String description;

    @TableField("sortIndex")
    private Integer sortIndex;

    /**
     * 路径  ==> 父级 到 子级  自动生成把
     */
    private String path;

    /**
     * 课程数量
     */
    @TableField("totalCount")
    private Integer totalCount;

    // 父级目录 对应的 子级目录
    @TableField(exist = false) // 查询时不映射该字段
    private List<CourseType> children = new ArrayList<>();


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
