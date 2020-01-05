package cn.itsource.hrm.staticPage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 19:08 2020/1/5
 * @Version : v1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"cn.itsource.hrm.client"})
public class StaticPageApplication_9008 {

    public static void main(String[] args) {
        SpringApplication.run(StaticPageApplication_9008.class,args);
    }
}
