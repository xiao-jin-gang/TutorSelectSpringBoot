package com.nwu.controller.admin;

import com.nwu.entities.ApplyType;
import com.nwu.results.Result;
import com.nwu.results.ResultCode;
import com.nwu.service.admin.impl.ApplyTypeServiceImpl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/*
 *  获取所有的申请类别控制层
 */
@RestController
@RequestMapping("/admin/apply-type")
public class ApplyTypeController {
    @Resource
    ApplyTypeServiceImpl applyTypeService;

    @GetMapping("/getApplyType")
    public Result getApplyType() {
        List<ApplyType> list = applyTypeService.list();
        return new Result(ResultCode.SUCCESS, list);
    }
}
