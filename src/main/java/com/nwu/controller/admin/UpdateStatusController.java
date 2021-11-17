package com.nwu.controller.admin;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.nwu.entities.Apply;
import com.nwu.mapper.admin.ApplyMapper;
import com.nwu.vo.QueryDepartmentSecretaryInit;
import com.nwu.vo.UpdateStatus;
import com.nwu.results.Result;
import com.nwu.service.admin.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/*
 *  进行审核操作,更新审核状态，添加备注
 */
@RestController
@RequestMapping("/admin/update-status")
public class UpdateStatusController {
    @Resource
    public ApplyService applyService;
    @Autowired
    private ApplyMapper applyMapper;

    //院系秘书初审页面的更新操作，修改状态，添加备注
    @PostMapping("/update")
    public Result updateFirstByDepartmentSecretary(@RequestBody List<UpdateStatus> list) throws Exception {
        if (list.size() > 0) {
            for (UpdateStatus s : list) {
                Integer id = s.getId_1();
                Integer status = s.getStatus_1();
                String commit = s.getCommit_1();
                int i = applyService.updateApplyStatus(id, status, commit);
            }
        }
        return Result.SUCCESS();
    }
    //社科、科研处更新操作，修改状态，添加备注
    @PostMapping("/updateSocial")
    public Result updateFirstBySocial(@RequestBody List<UpdateStatus> list) throws Exception {
        if (list.size() > 0) {
            for (UpdateStatus s : list) {
                Integer id = s.getId_1();
                Integer status = s.getStatus_1();
                String commit = s.getCommit_1();
                int i = applyService.updateSocialApplyStatus(id, status, commit);
            }
        }
        return Result.SUCCESS();
    }

    //院系秘书提交分会页面的更新操作，修改状态，添加备注
    @PostMapping("/updateSfh")
    public Result updateSubmitChapterByDepartmentSecretary(@RequestBody List<UpdateStatus> list) throws Exception {
        if (list.size() > 0) {
            for (UpdateStatus s : list) {
                Integer id = s.getId_1();
                Integer status = s.getStatus_1();
                String commit = s.getCommit_1();
                int i = applyService.updateApplyStatusSfh(id,status,commit);
            }
        }
        return Result.SUCCESS();
    }
    //院系秘书录入分会意见页面的更新操作，修改状态，添加备注
    @PostMapping("/updateXy")
    public Result updateEnterChapterByDepartmentSecretary(@RequestBody List<UpdateStatus> list) throws Exception {
        if (list.size() > 0) {
            for (UpdateStatus s : list) {
                Integer id = s.getId_1();
                Integer status = s.getStatus_1();
                String commit = s.getCommit_1();
                int i = applyService.updateApplyStatusAndCommitXy(id,status,commit);
            }
        }
        return Result.SUCCESS();
    }

    //提交按钮后的更新操作，将符合条件的变为同意上校分会状态
    @Transactional
    @PostMapping("/submitUpdate")
    public Result updateStatusBySubmit() throws Exception {
            applyService.updateApply388to38();
       //     applyService.updateApply399to39();
            return Result.SUCCESS();
    }
    //研究生院管理员修改备注
    @PostMapping("/updateCommitByGraduate")
    public Result updateCommitByGraduate(@RequestBody QueryDepartmentSecretaryInit submit) throws Exception {
        LambdaUpdateChainWrapper<Apply> lambdaUpdateChainWrapper = new LambdaUpdateChainWrapper<>(applyMapper);


        boolean res = lambdaUpdateChainWrapper.eq(Apply::getApplyId,submit.getApplyId())
                .set(Apply::getCommitYjsyCs,submit.getCommitYjsyCs())
                .set(Apply::getCommitYjsyLr,submit.getCommitYjsyLr())
                .set(Apply::getCommitYjsySfh,submit.getCommitYjsySfh()).update();
        if(res) {
            return Result.SUCCESS();
        }else{
            return Result.FAIL();
        }
    }

}
