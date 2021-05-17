package com.share.happy.mange;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.share.happy.dao")
@ComponentScan(basePackages={"com.share.happy.api"})//扫描接口 swagger
@ComponentScan(basePackages={"com.share.happy.mange"})//扫描本项目下的所有类
@ComponentScan(basePackages={"com.share.happy.common"})//扫描common包下的类
@ComponentScan(basePackages={"com.share.happy.model"})//扫描common包下的类
public class MangeShareHappyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MangeShareHappyApplication.class,args);
    }
}
