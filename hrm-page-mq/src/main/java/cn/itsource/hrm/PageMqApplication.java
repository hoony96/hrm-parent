package cn.itsource.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 10:18 2020/1/6
 * @Version : v1.0
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"cn.itsource.hrm.client"})
public class PageMqApplication {


    public static void main(String[] args) {
        SpringApplication.run(PageMqApplication.class,args);
    }
}
