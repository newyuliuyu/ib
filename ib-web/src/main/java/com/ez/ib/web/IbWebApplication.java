package com.ez.ib.web;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class})
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.ez.**"})
public class IbWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(IbWebApplication.class, args);
    }
}
