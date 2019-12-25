package cn.itsource.hrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: hoony96
 * @Description: TODO
 * @Date: Create in 20:49 2019/12/24
 * @Version : v1.0
 */
@SpringBootApplication
@MapperScan("cn.itsource.hrm.mapper")
@EnableTransactionManagement
public class SystemServiceApplication_9001 {

    public static void main(String[] args) {
        SpringApplication.run(SystemServiceApplication_9001.class,args);
    }
}
