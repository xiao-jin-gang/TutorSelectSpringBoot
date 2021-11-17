package com.nwu.controller;


import com.nwu.entities.Role;
import com.nwu.results.Result;
import com.nwu.results.ResultCode;
import com.nwu.service.impl.RoleServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色前端控制器
 * </p>
 *
 * @author dynamic
 * @since 2021-08-09
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController {
    @Autowired
    private RoleServiceImpl roleService;

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/getAll")
    public Result getAll() {
        List<Role> roles = roleService.list(null);
        return new Result(ResultCode.SUCCESS,roles);
    }
}

