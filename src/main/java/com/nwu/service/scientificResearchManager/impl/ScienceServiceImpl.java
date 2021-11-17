package com.nwu.service.scientificResearchManager.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.nwu.entities.*;
import com.nwu.service.SystemUserService;
import com.nwu.service.admin.ApplyService;
import com.nwu.service.scientificResearchManager.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScienceServiceImpl implements ScienceService {

    @Resource
    private AcademicPaperService academicPaperService;

    @Resource
    private AcademicWorksService academicWorksService;

    @Resource
    private ResearchProjectService researchProjectService;

    @Resource
    private InventionPatentService inventionPatentService;

    @Resource
    private TeachingAwardsService teachingAwardsService;

    @Resource
    private ApplyService applyService;

    @Resource
    private SystemUserService systemUserService;

    @Override
    public JSONObject getInit(String tutorId, int applyId) {

        JSONObject object = new JSONObject();

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("tutor_id", tutorId);
        // 获取教师基本信息
        SystemUser one = systemUserService.getOne(wrapper);
        object.put("tutorId", one.getTutorId());
        object.put("name", one.getName());
        object.put("organizationName", one.getOrganizationName());

        wrapper.eq("apply_id", applyId);

        try {
            // 获取学术论文
            List<AcademicPaper> paperList = academicPaperService.list(wrapper);
            object.put("paperList", paperList);

            // 获取科研项目
            List<ResearchProject> projectList = researchProjectService.list(wrapper);
            object.put("projectList", projectList);

            // 获取教材或学术著作
            List<AcademicWorks> workList = academicWorksService.list(wrapper);
            object.put("workList", workList);

            // 获取科研教学奖励
            List<TeachingAwards> awardList = teachingAwardsService.list(wrapper);
            object.put("awardList", awardList);

            // 获取发明专利
            List<InventionPatent> patentList = inventionPatentService.list(wrapper);
            object.put("patentList", patentList);



        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }

    @Override
    public void check(int id, int type, int passOrReject, String col2) {

        switch (type) {
            // 学术论文
            case 1:
                UpdateWrapper<AcademicPaper> wrapper1 = new UpdateWrapper<>();
                wrapper1.eq("paper_id", id);
                // 论文通过
                if (passOrReject == 1) {
                    wrapper1.set("col1", "通过");
                    wrapper1.set("col2", "");
                } else {
                    wrapper1.set("col1", "不通过");
                    wrapper1.set("col2", col2);
                }
                academicPaperService.update(wrapper1);
                break;
            case 2:
                UpdateWrapper<ResearchProject> wrapper2 = new UpdateWrapper<>();
                wrapper2.eq("project_id", id);
                // 论文通过
                if (passOrReject == 1) {
                    wrapper2.set("col1", "通过");
                    wrapper2.set("col2", "");
                } else {
                    wrapper2.set("col1", "不通过");
                    wrapper2.set("col2", col2);
                }
                researchProjectService.update(wrapper2);
                break;
            case 3:
                UpdateWrapper<AcademicWorks> wrapper3 = new UpdateWrapper<>();
                wrapper3.eq("works_id", id);
                // 论文通过
                if (passOrReject == 1) {
                    wrapper3.set("col1", "通过");
                    wrapper3.set("col2", "");
                } else {
                    wrapper3.set("col1", "不通过");
                    wrapper3.set("col2", col2);
                }
                academicWorksService.update(wrapper3);
                break;
            case 4:
                UpdateWrapper<TeachingAwards> wrapper4 = new UpdateWrapper<>();
                wrapper4.eq("awards_id", id);
                // 论文通过
                if (passOrReject == 1) {
                    wrapper4.set("col1", "通过");
                    wrapper4.set("col2", "");
                } else {
                    wrapper4.set("col1", "不通过");
                    wrapper4.set("col2", col2);
                }
                teachingAwardsService.update(wrapper4);
                break;
            case 5:
                UpdateWrapper<InventionPatent> wrapper5 = new UpdateWrapper<>();
                wrapper5.eq("patent_id", id);
                // 论文通过
                if (passOrReject == 1) {
                    wrapper5.set("col1", "通过");
                    wrapper5.set("col2", "");
                } else {
                    wrapper5.set("col1", "不通过");
                    wrapper5.set("col2", col2);
                }
                inventionPatentService.update(wrapper5);
                break;
            default: break;
        }
    }

    @Override
    public void commitMaterial(int applyId, int type, String commitSocial, int science) {
        UpdateWrapper<Apply> wrapper = new UpdateWrapper<>();
        // 审核通过
        if (type == 1) {
            if (science == 1) {
                wrapper.set("status", 301);
            } else {
                wrapper.set("status", 311);
            }
            wrapper.set("commit_social", "");
        }
        // 审核不通过
        else {
            if (science == 1) {
                wrapper.set("status", 302);
            } else {
                wrapper.set("status", 312);
            }
            wrapper.set("commit_social", commitSocial);
        }
        wrapper.eq("apply_id", applyId);
        applyService.update(wrapper);
    }
}
