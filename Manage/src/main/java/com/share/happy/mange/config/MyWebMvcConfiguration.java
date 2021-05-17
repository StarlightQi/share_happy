package com.share.happy.mange.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MyWebMvcConfiguration extends WebMvcConfigurerAdapter {
    //配置本地文件映射到url上
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").
                addResourceLocations("file:D:/static/image/");//定义图片存放路径
       registry.addResourceHandler("/css/**").
                addResourceLocations("file:D:/static/css/");//定义图片存放路径
        registry.addResourceHandler("/html/**").
                addResourceLocations("file:D:/static/html/");//定义图片存放路径
    }

}
