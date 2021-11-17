package com.nwu.config;

import com.nwu.filter.SecurityFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Rex Joush
 * @time 2021.09.09 10:17
 */

/**
 * 控制权限的拦截器
 */
//@Configuration
public class AuthorizationConfig {

//    @Bean
//    public FilterRegistrationBean<SecurityFilter> testFilterRegistration() {
//        FilterRegistrationBean<SecurityFilter> registration = new FilterRegistrationBean<>(new SecurityFilter());
//        registration.addUrlPatterns("/*"); //
//        registration.setName("securityFilter");
//        registration.setOrder(6);
//        return registration;
//    }

}
