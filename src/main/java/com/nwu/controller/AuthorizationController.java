package com.nwu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nwu.entities.SystemUser;
import com.nwu.service.AuthorizationService;
import com.nwu.service.SystemUserService;
import com.nwu.util.AESUtil;
import com.nwu.util.DataUtils;
import com.nwu.util.IdUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rex Joush
 * @time 2021.08.24 20:46
 */

@RestController
@CrossOrigin
public class AuthorizationController {

    @Resource
    private AuthorizationService authorizationService;

    @Resource
    private SystemUserService systemUserService;

//    @GetMapping("/")
//    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        System.out.println("hello");
//        response.sendRedirect("/index.html");
//    }

    // 获取token信息
    @PostMapping("/user/login")
    public String login(HttpServletRequest request) {

        // 获取到登录名
        String tutorId = IdUtils.getTutorId(request);
        System.out.println("username " + tutorId);

        // 获取权限 tutor  departmentSecretary
        String authorization = authorizationService.getAuthorization(tutorId);

        try {
            if (authorization == null) {
                authorization = "tutor";
                int i = systemUserService.addSystemUser(tutorId);
                if (i < 0) {
                    JSONObject object = new JSONObject();
                    object.put("code", 20001);
                    return JSON.toJSONString(object);
                }
            }
        } catch (Exception e) {
            JSONObject object = new JSONObject();
            object.put("code", 20001);
            return JSON.toJSONString(object);
        }

        JSONObject object = new JSONObject();
        // 将 学号 + 权限 加密后写入 token
        object.put("code", 20000);
        object.put("data", Map.of("token", AESUtil.encode(tutorId + "+" + authorization)));

        return JSON.toJSONString(object);
    }

    // 获取个人信息
    @GetMapping("/user/info")
    public String info(@RequestParam("token") String token) {


        // 解密 token 值拿到 学号 + 权限
        String decode = AESUtil.decode(token);
        //根据学号去查询对应的院系id

        // 权限列表
        List<String> roles = new ArrayList<>();

        // 查询权限
        String authorization = authorizationService.getAuthorization(decode.split("[+]")[0]);

        QueryWrapper<SystemUser> wrapper = new QueryWrapper<>();

        wrapper.eq("tutor_id", decode.split("[+]")[0]);
        SystemUser systemUser = systemUserService.getOne(wrapper);
        int organizationId = -1;
        String organizationName = "";
        if (systemUser != null) {
            organizationId = systemUser.getOrganizationId();
            organizationName = systemUser.getOrganizationName();
        }
        roles.add(authorization);

        JSONObject object = new JSONObject();
        object.put("code", 20000);
        object.put("data", Map.of("roles", roles));
        object.put("organizationId", organizationId);
        object.put("organizationName", organizationName);

        return JSON.toJSONString(object);

    }

    @PostMapping("/user/logout")
    public Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        String casLogoutUrl = DataUtils.casURL + "/logout";
        String redirectURL = casLogoutUrl + "?service="+ URLEncoder.encode(DataUtils.appURL);
        Map<String, Object> result = new HashMap<>();

        result.put("code", 20000);
        result.put("data", redirectURL);
        return result;
    }

}
