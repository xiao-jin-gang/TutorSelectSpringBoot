package com.nwu.controller.scientificResearchManager;

import com.alibaba.fastjson.JSONObject;
import com.nwu.results.Result;
import com.nwu.results.ResultCode;
import com.nwu.service.scientificResearchManager.ScienceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 社科处和科研处公用 controller
 */
@RestController
@RequestMapping("/admin/science")
public class ScienceController {

    @Resource
    private ScienceService scienceService;

    @GetMapping("/init/{tutorId}/{applyId}")
    public Result getInit(@PathVariable("tutorId") String tutorId,
                          @PathVariable("applyId") int applyId) {

        JSONObject result = scienceService.getInit(tutorId, applyId);
        return new Result(ResultCode.SUCCESS, result);
    }

    @PostMapping("/check/{id}/{type}/{passOrReject}")
    public Result check(@PathVariable("id") int id,
                        @PathVariable("type") int type,
                        @PathVariable("passOrReject") int passOrReject,
                        @RequestBody JSONObject reject) {

        scienceService.check(id, type, passOrReject, reject.getString("col2"));
        return new Result(ResultCode.SUCCESS);

    }

    @PostMapping("/commitMaterial/{applyId}/{type}/{science}")
    public Result commitMaterial(@PathVariable("applyId") int applyId,
                                 @PathVariable("type") int type,
                                 @PathVariable("science") int science,
                                 @RequestBody JSONObject commitSocial) {

        scienceService.commitMaterial(applyId, type, commitSocial.getString("commitSocial"), science);
        return new Result(ResultCode.SUCCESS);

    }

}
