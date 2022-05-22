package com.starry.systemService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author Satrry
 * @Description TODO
 * @Date 2022/5/21 23:10
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan({"com.starry.commonService","com.starry.*.mapper"})
@EnableSwagger2
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class,args);
    }
}
