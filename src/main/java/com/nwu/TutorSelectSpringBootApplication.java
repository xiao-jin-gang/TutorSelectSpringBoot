package com.nwu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin // 配置跨域支持
@MapperScan("com.nwu.mapper")
@SpringBootApplication
public class TutorSelectSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorSelectSpringBootApplication.class, args);
    }

}
