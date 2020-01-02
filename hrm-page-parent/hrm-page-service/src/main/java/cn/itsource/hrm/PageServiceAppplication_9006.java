package cn.itsource.hrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 19:34 2020/1/2
 * @Version : v1.0
 */
@SpringBootApplication
@EnableFeignClients
@MapperScan("cn.itsource.hrm.mapper")
@EnableSwagger2
public class PageServiceAppplication_9006 {


    public static void main(String[] args) {


        SpringApplication.run(PageServiceAppplication_9006.class,args);
    }

}
