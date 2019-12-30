package cn.itsource.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 16:04 2019/12/27
 * @Version : v1.0
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigServerApplication_1299 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication_1299.class,args);
    }
}
