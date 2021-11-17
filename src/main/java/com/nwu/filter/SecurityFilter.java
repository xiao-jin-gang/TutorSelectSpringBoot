package com.nwu.filter;

import com.nwu.util.AESUtil;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Rex Joush
 * @time 2021.08.13 15:47
 */
//@Configuration
public class SecurityFilter /*implements Filter*/ {

//    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 获取请求的 uri
        String requestURI = request.getRequestURI();

        if ("/".equals(requestURI.split(";")[0])) {
            filterChain.doFilter(request, response);
            return;
        }

        // 获取请求的前缀地址，/user, /user/a 获取到 user
        String requestPrefix = requestURI.split("/")[1];

        // System.out.println("requestURI: " + requestURI);
        // System.out.println("requestPrefix: " + requestPrefix);

        // 登录，info，登出，直接放行
        if ("user".equals(requestPrefix)) {
            filterChain.doFilter(request, response);
            return;
        }
        if ("static".equals(requestPrefix)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 图片和压缩包等资源直接放行
        if (requestURI.endsWith(".ico") ||
                requestURI.endsWith("jpg") ||
                requestURI.endsWith("png") ||
                requestURI.endsWith("rar") ||
                requestURI.endsWith("zip") ||
                requestURI.endsWith("pdf") ||
                requestURI.endsWith(".html"))
        {
            filterChain.doFilter(request, response);
            return;
        }


        // 其他请求，先获取权限
        String token = request.getHeader("token");

        // 如果没有权限，直接拒绝
        if (token == null) {
            System.out.println("无权限");
            response.setStatus(403);
            System.out.println("无权限");
            return;
        }

        // 获取权限
        String authorization = AESUtil.decode(token).split("[+]")[1];

        // 管理员角色直接放行
        if ("admin".equals(authorization)) {
            filterChain.doFilter(request, response);
        } else {

            // 公共请求前缀，照片请求，所有角色均放行
            if ("common".equals(requestPrefix) || "downFile".equals(requestPrefix)) {
                filterChain.doFilter(request, response);
                return;
            }
            // 放行所以管理员的请求，以 admin 为请求前缀
            if (!"tutor".equals(authorization) && "admin".equals(requestPrefix)) {
                filterChain.doFilter(request, response);
                return;
            }
            // 如果用户权限和当前的请求前缀相同，则放行
            if (authorization.equals(requestPrefix)) {
                filterChain.doFilter(request, response);
            }
            // 否则拒绝
            else {
                response.setStatus(403);
            }
        }
    }
}
