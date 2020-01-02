package cn.itsource.hrm.web.dto;

import lombok.Data;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 21:07 2020/1/2
 * @Version : v1.0
 */
@Data
public class PagerDto {


    private Long id;

    /**
     * 英文名
     */
    private String name;

    /**
     * 别名
     */
    private String alias;

    private Long type;

    private String physicalPath;

    private Long createTime = System.currentTimeMillis();

    private Long siteId;

    /**
     * 模板在hdfs中的路径地址
     */
    private String templateUrl;


    private String dataKey;

    private String pageUrl;
}
