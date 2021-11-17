package com.nwu.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nwu.results.Result;
import com.nwu.results.ResultCode;
import com.nwu.service.TutorInspectService;
import com.nwu.service.tutor.PageInit;
import com.nwu.vo.QueryDepartmentSecretaryInit;
import com.nwu.vo.TutorQuery;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dynamic
 * @since 2021-08-09
 */
@RestController
@RequestMapping("/admin/tutor-inspect")
public class TutorInspectController {

    @Resource
    private TutorInspectService tutorInspectService;

    @PostMapping("/getInit")
    public Result getInit(@RequestParam("organizationId") int organizationId,
                          @RequestParam("applyStatuss") List<String> applyStatuss,
                          @RequestParam("pageNumber") int pageNumber) {

        System.out.println(organizationId);
        System.out.println(applyStatuss);

        List<QueryDepartmentSecretaryInit> inits;
        int total;
        try {
            inits = tutorInspectService.getTutorInitOrSearch(organizationId, applyStatuss, pageNumber, null, 0);
            total = tutorInspectService.getTutorInitOrSearchTotal(organizationId, applyStatuss, null, 0);
        } catch (Exception e) {
            return new Result(ResultCode.SUCCESS, PageInit.getErrorMessage(e));
        }
        JSONObject object = new JSONObject();
        object.put("data", inits);
        object.put("total", total);
        return new Result(ResultCode.SUCCESS, object);
    }

    /**
     * 查询接口，查询所有的申请信息
     * @param tutorQuery 申请信息
     * @param pageNumber 页码
     * @return
     */
    @PostMapping("/search/{pageNumber}")
    public Result search(@RequestBody TutorQuery tutorQuery,
                         @PathVariable("pageNumber") int pageNumber) {

        System.out.println(tutorQuery);
        System.out.println(pageNumber);

        List<QueryDepartmentSecretaryInit> inits;
        int total;
        try {
            inits = tutorInspectService.getTutorInitOrSearch(tutorQuery.getOrganization(), tutorQuery.getApplyStatuss(), pageNumber, tutorQuery, 1);
            total = tutorInspectService.getTutorInitOrSearchTotal(tutorQuery.getOrganization(), tutorQuery.getApplyStatuss(), tutorQuery, 1);
        } catch (Exception e) {
            return new Result(ResultCode.SUCCESS, PageInit.getErrorMessage(e));
        }
        JSONObject object = new JSONObject();
        object.put("data", inits);
        object.put("total", total);
        return new Result(ResultCode.SUCCESS, object);
    }

}

