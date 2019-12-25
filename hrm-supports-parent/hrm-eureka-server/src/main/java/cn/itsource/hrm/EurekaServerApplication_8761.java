package cn.itsource.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 18:53 2019/12/24
 * @Version : v1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication_8761 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication_8761.class,args);
    }
}
