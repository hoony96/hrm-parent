package cn.itsource.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 16:43 2019/12/27
 * @Version : v1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class RedisServiceApplication_9003 {

    public static void main(String[] args) {
        SpringApplication.run(RedisServiceApplication_9003.class,args);
    }
}
