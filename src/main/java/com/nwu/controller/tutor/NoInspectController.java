package com.nwu.controller.tutor;

/**
 * @author Rex Joush
 * @time 2021.09.12 17:29
 */

import com.nwu.entities.Apply;
import com.nwu.entities.tutor.FirstPage;
import com.nwu.entities.tutor.NoSecondPage;
import com.nwu.results.Result;
import com.nwu.results.ResultCode;
import com.nwu.service.tutor.PageInit;
import com.nwu.service.tutor.common.TeacherInfoService;
import com.nwu.service.tutor.noInspectApply.NoFirstService;
import com.nwu.service.tutor.noInspectApply.NoSecondService;
import com.nwu.util.IdUtils;
import com.nwu.util.TimeUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 免审相关控制器
 */
@RestController
@RequestMapping("/tutor")
public class NoInspectController {


    // 第一页
    @Resource
    private NoFirstService noFirstService;

    // 免审第二页 com.nwu.entities.tutor.noInspect.SecondPage;
    @Resource
    private NoSecondService noSecondService;

    @Resource
    private TeacherInfoService teacherInfoService;

    /**
     * 获取第一页导师基本信息
     *
     * @param applyId 申请表 id
     * @return firstPage
     */
    @GetMapping("/noInspect/getFirstPage/{applyId}")
    public Result getFirstPage(@PathVariable("applyId") Integer applyId,
                               HttpServletRequest request) {

        String tutorId = IdUtils.getTutorId(request);

        FirstPage noFirstPage;
        FirstPage firstPage;
        if (applyId == null) {
            return new Result(ResultCode.FAIL);
        }
        try {
            if (applyId == -1) {
                // 未申请过，查询基本信息
                firstPage = teacherInfoService.getTeacherInfo(tutorId);
                return new Result(ResultCode.SUCCESS, firstPage);
            } else {
                // 已申请过 查询 tutor_no_inspect 表的信息
                noFirstPage = noFirstService.getNoFirstPage(applyId);
                System.out.println(noFirstPage);
                return new Result(ResultCode.SUCCESS, noFirstPage);
            }
        } catch (Exception e) {
            return new Result(ResultCode.FAIL);
        }
    }

    /**
     * 保存第一页免审信息
     *
     * @param noFirstPage    第一页具体信息
     * @param applyId        申请 id
     * @param applyTypeId    申请类型 id
     * @param applyCondition 申请状态
     * @param request        request 对象
     */
    @PostMapping("/noInspect/saveFirstPage/{applyId}/{applyTypeId}/{applyCondition}")
    public Result saveFirstPage(@RequestBody FirstPage noFirstPage,
                                @PathVariable("applyId") int applyId,
                                @PathVariable("applyTypeId") int applyTypeId,
                                @PathVariable("applyCondition") int applyCondition,
                                HttpServletRequest request) {

        // String tutorId = IdUtils.getTutorId(request);

        // 已经申请过此岗位，但信息未填写完成，更新第一页信息
        if (applyCondition == 101) {
            // 更新第一页
            noFirstService.updateNoFirstPage(noFirstPage.getApplyId(), noFirstPage.getPhone(), noFirstPage.getEmail(), noFirstPage.getEvaluateTime(), noFirstPage.getAwardDepartment() + " " + noFirstPage.getAwardTime());
            // 读取第二页
            NoSecondPage secondPage = noSecondService.getSecondPage(applyId);
            System.out.println("secondPage");
            System.out.println(secondPage);
            return new Result(ResultCode.SUCCESS, secondPage);
        }

        /*
            未申请过，需要添加申请表
         */
        Apply apply = new Apply();

        // 设置申请的类型和教师工号
        apply.setTutorId(noFirstPage.getTutorId());
        apply.setName(noFirstPage.getName());
        apply.setOrganizationId(noFirstPage.getOrganizationId());
        apply.setApplyTypeId(applyTypeId);
        apply.setStatus(0);
        apply.setSubmitTime(TimeUtils.sdf.format(new Date()));

        // 添加申请表
        noFirstService.saveNoApplyInfo(apply);

        // 设置 id 值
        noFirstPage.setApplyId(String.valueOf(apply.getApplyId()));

        /*
            返回第二页的结果
        */
        NoSecondPage secondPage;
        try {
            // 插入基本信息表
            noFirstService.saveNoFirstPage(noFirstPage, request);
            // 102 表示未申请过，第二页无信息，否则取读取第二页信息
            if (applyCondition == 102) {
                secondPage = PageInit.getNoSecondPage();
                secondPage.setApplyId(apply.getApplyId());
            } else {
                secondPage = noSecondService.getSecondPage(apply.getApplyId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAIL, e.getMessage());
        }
        System.out.println(secondPage);
        return new Result(ResultCode.SUCCESS, secondPage);

    }

    @ApiOperation("保存免审第二页信息")
    @PostMapping("/noInspect/saveSecondPage/{applyId}")
    public Result saveSecondPage(@RequestBody NoSecondPage noSecondPage,
                                 @PathVariable("applyId") int applyId) {

        // 第二页无论什么都要更新数据库

        if (noSecondPage != null) {
            try {
                noSecondService.updateNoSecondPage(noSecondPage, applyId);
            } catch (Exception e) {
                // 出现异常
                return new Result(ResultCode.SUCCESS, PageInit.getErrorMessage(e));
            }
        }
        return new Result(ResultCode.SUCCESS, 1200);
    }
}
