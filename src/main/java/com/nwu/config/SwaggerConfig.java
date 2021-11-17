package com.nwu.config;

/**
 * @author Rex Joush
 * @time 2021.08.08 17:01
 */

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName : SwaggerConfig  //类名
 * @Description : Swagger2的配置类  //描述
 * @Author : Dynamic  //作者
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("多信息统一管控平台-API文档")
                .description("本文档描述了多信息统一管控平台中的微服务接口定义")
                .version("1.0")
                .contact(new Contact("dynamic", "http://www.nwu.edu.com", "1115718363@qq.com"))
                .build();
    }

}

