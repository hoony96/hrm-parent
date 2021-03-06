package cn.itsource.hrm.swaager;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("系统管理", "/services/system/v2/api-docs", "2.0"));
        resources.add(swaggerResource("课程管理", "/services/course/v2/api-docs", "2.0"));
        resources.add(swaggerResource("文件上传", "/services/file/v2/api-docs", "2.0"));
        resources.add(swaggerResource("页面静态化", "/services/page/v2/api-docs", "2.0"));

        return resources;

    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}