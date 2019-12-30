package cn.itsource.hrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 19:49 2019/12/25
 * @Version : v1.0
 */
@SpringBootApplication
@MapperScan("cn.itsource.hrm.mapper")
@EnableSwagger2
// 开启feign  后面可以指定扫描的包
@EnableFeignClients("cn.itsource.hrm.client")
public class CourseServiceApplication_9002 {
    public static void main(String[] args) {
        SpringApplication.run(CourseServiceApplication_9002.class,args);
    }
}
