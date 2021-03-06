package cn.itsource.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 16:47 2019/12/30
 * @Version : v1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class FastdfsServiceApplication_9004 {
    public static void main(String[] args) {
        SpringApplication.run(FastdfsServiceApplication_9004.class,args);
    }
}
