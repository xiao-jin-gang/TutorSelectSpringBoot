package com.nwu.controller.tutor;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.nwu.entities.Apply;
import com.nwu.entities.SystemTime;
import com.nwu.entities.tutor.ApplyDetails;
import com.nwu.entities.tutor.ApplyDisplay;
import com.nwu.results.Result;
import com.nwu.results.ResultCode;
import com.nwu.service.SystemTimeService;
import com.nwu.service.admin.ApplyService;
import com.nwu.service.tutor.PageInit;
import com.nwu.util.AESUtil;
import com.nwu.util.IdUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2021.09.03 15:19
 */
@RestController
public class MyApplyController {

    @Resource
    private ApplyService applyService;

    @Resource
    private SystemTimeService systemTimeService;

    /**
     * 获取当前教师的所有申请信息
     */
    @GetMapping("/tutor/getApplyList")
    public Result getApplyList(HttpServletRequest request) {

        String tutorId = IdUtils.getTutorId(request);

        String token = request.getHeader("token");
        System.out.println(token);
        System.out.println(AESUtil.decode(token));

        List<ApplyDisplay> applyList = applyService.getApplyList(tutorId);

        return new Result(ResultCode.SUCCESS, applyList);

    }

    /**
     * 将当前的申请表状态改为 0，使得教师可以进行修改，或将填写完成的申请交由院系秘书审核
     * @param applyId 申请表 id
     * @param status 类型，1，将申请由 14 改为 0
     *                    2，将申请由 6 改为 10
     */
    @GetMapping("/tutor/changeStatus/{applyId}/{status}")
    public Result changeStatus(@PathVariable("applyId") int applyId,
                              @PathVariable("status") int status) {

        System.out.println(applyId);
        try {
            if (status == 1) {
                // 将状态码 14 改为 0，让教师修改被驳回的申请
                applyService.updateApplyStatus(applyId, 0, "");
            } else {
                // 将状态码 6 改为 10，提交申请至院系秘书
                UpdateWrapper<Apply> wrapper = new UpdateWrapper<>();
                wrapper.set("status", 10);
                wrapper.set("commit", "");
                wrapper.set("commit_yjsy_cs", "");
                wrapper.set("commit_yjsy_lr", "");
                wrapper.set("commit_yjsy_sfh", "");
                wrapper.set("commit_social", "");
                wrapper.set("commit_yx_xy", "");
                wrapper.set("commit_yx_sfh", "");
                wrapper.eq("apply_id", applyId);
                applyService.update(wrapper);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 1201);
            jsonObject.put("message", "网络出现异常，请稍后再试");
            jsonObject.put("errorMessage", e.getMessage());
            return new Result(ResultCode.SUCCESS, jsonObject);
        }
        return new Result(ResultCode.SUCCESS);
    }


    @GetMapping("/tutor/getOrganizationTime/{organizationId}")
    public Result getTime(@PathVariable Integer organizationId) {

        QueryWrapper<SystemTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("organization_id", organizationId);
        SystemTime res = systemTimeService.getOne(queryWrapper);
        return new Result(ResultCode.SUCCESS, res);

    }

    @GetMapping("/common/tutor/getApplyDetails/{applyId}/{isInspect}/{tutorId}")
    public Result getApplyDetails(@PathVariable("applyId") int applyId,
                                  @PathVariable("isInspect") int isInspect,
                                  @PathVariable("tutorId") String tutorId,
                                  HttpServletRequest request) {

//        String tutorId = IdUtils.getTutorId(request);

        System.out.println(isInspect);// 1 非免审，0 免审

        ApplyDetails applyDetails = null;
        try {
            applyDetails = applyService.getApplyDetails(applyId, isInspect, tutorId);
        } catch (Exception e) {
            return new Result(ResultCode.SUCCESS, PageInit.getErrorMessage(e));
        }

        return new Result(ResultCode.SUCCESS, applyDetails);
    }

}
