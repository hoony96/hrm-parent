package cn.itsource.hrm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 19:34 2019/12/25
 * @Version : v1.0
 */
@ConfigurationProperties(prefix = "hrm.starter")
@Data
public class SwaggerProperties {

    private String basePackage = "cn.itsource.hrm.web.controller";
    private String title;
    private String description;
    private String name = "hoony96";
    private String url = "";
    private String email = "229370431@qq.com";
    private String version = "1.0";

}
