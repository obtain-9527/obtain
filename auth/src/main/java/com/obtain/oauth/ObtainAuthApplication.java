package com.obtain.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author obtain_y
 * @date 2020/11/22 13:59
 * @description com.obtain.oauth   暂且用数据库去除数据库
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ObtainAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ObtainAuthApplication.class);
    }
}
