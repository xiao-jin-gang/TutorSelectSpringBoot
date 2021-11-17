package com.nwu.config;

import com.nwu.util.DataUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UploadMappingConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //注册，所有请求到upfile下的所有资源 我们映射到磁盘上的位置上
        //Windows用
        registry
                .addResourceHandler("/downFile/**")
                .addResourceLocations("file:"+ DataUtils.basePath);
        //                .addResourceLocations("file:D:/RARZIP/");
//        registry
//                .addResourceHandler("/downImage/**")
//                .addResourceLocations("file:E:/RARZIP/");
        //LINUX用
//        registry.addResourceHandler("/img/**").addResourceLocations("file:/usr/local/img/");
    }
}
