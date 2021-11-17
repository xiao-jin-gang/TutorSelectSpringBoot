package com.nwu.service.tutor.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nwu.entities.*;
import com.nwu.entities.tutor.ThirdPage;
import com.nwu.entities.tutor.childSubject.DeleteItem;
import com.nwu.service.scientificResearchManager.*;
import com.nwu.service.tutor.PageInit;
import com.nwu.service.tutor.SummaryService;
import com.nwu.service.tutor.common.DeleteFileService;
import com.nwu.service.tutor.common.ThirdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Rex Joush
 * @time 2021.08.29 20:42
 */
@Service
public class ThirdServiceImpl implements ThirdService {

    @Resource
    private AcademicPaperService academicPaperService; // 论文服务类

    @Resource
    private ResearchProjectService researchProjectService; // 科研项目服务类

    @Resource
    private AcademicWorksService academicWorksService; // 教材或学术著作服务类

    @Resource
    private TeachingAwardsService teachingAwardsService; // 科研教学奖励服务类

    @Resource
    private InventionPatentService inventionPatentService; // 发明专利服务类

    @Resource
    private SummaryService summaryService; // 汇总服务类

    @Resource
    private DeleteFileService deleteFileService; //删除文件

    @Override
    public ThirdPage updateOrSaveThirdPage(int applyId, String tutorId, ThirdPage thirdPage, int learningType, HttpServletRequest request) {

        ThirdPage thirdPageOne = new ThirdPage();
        switch (learningType) {  //保存类别
            case 1:
            case 2:
                // 设置论文
                try {
                    if (thirdPage.getAcademicPapers() != null) {
                        for (AcademicPaper academicPaper : thirdPage.getAcademicPapers()) {
                            academicPaper.setApplyId(applyId);
                            academicPaper.setTutorId(tutorId);
                            academicPaper.setCol1("");
                            academicPaper.setCol2("");
                            academicPaperService.saveOrUpdate(academicPaper);
                        }
                    }
                    if (thirdPage.getDeleteItems() != null) {
                        //获取论文项 删除论文 删除的类型
                        for (DeleteItem item : thirdPage.getDeleteItems()) {
                            if (item.getDeleteType() == 1 && item.getDeleteId() != -1) {
                                // 根据id删除
                                academicPaperService.removeById(item.getDeleteId());
                                // String path = deleteItem.getDeletePath();
                                // 删除文件
                                System.out.println(item.getDeletePath());
                                deleteFileService.delFile(item.getDeletePath(), request);
                            }
                        }
                    }
                    QueryWrapper<AcademicPaper> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("tutor_id", tutorId).eq("apply_id", applyId);
                    thirdPageOne.setAcademicPapers(academicPaperService.list(queryWrapper));
                } catch (Exception e) {
                    throw new RuntimeException("论文信息填写错误，请检查" + "!" + e.getMessage());
                }
                break;
            case 3:
                // 设置科研项目
                try {
                    if (thirdPage.getResearchProjects() != null) {
                        for (ResearchProject researchProject : thirdPage.getResearchProjects()) {
                            researchProject.setApplyId(applyId);
                            researchProject.setTutorId(tutorId);
                            researchProject.setCol1("");
                            researchProject.setCol2("");
                            researchProjectService.saveOrUpdate(researchProject);
                        }
                    }
                    if (thirdPage.getDeleteItems() != null) {
                        // 获取论文项 删除论文 删除的类型
                        for (DeleteItem item : thirdPage.getDeleteItems()) {
                            if (item.getDeleteType() == 3 && item.getDeleteId() != -1) {
                                // 根据id删除
                                researchProjectService.removeById(item.getDeleteId());
                                deleteFileService.delFile(item.getDeletePath(), request);
                            }
                        }
                    }
                    QueryWrapper<ResearchProject> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("tutor_id", tutorId).eq("apply_id", applyId);
                    thirdPageOne.setResearchProjects(researchProjectService.list(queryWrapper));
                } catch (Exception e) {
                    throw new RuntimeException("科研项目填写错误，请检查" + "!" + e.getMessage());
                }
                break;
            case 4:
                // 设置教材或学术著作
                try {
                    if (thirdPage.getAcademicWorks() != null) {
                        for (AcademicWorks academicWork : thirdPage.getAcademicWorks()) {
                            academicWork.setApplyId(applyId);
                            academicWork.setTutorId(tutorId);
                            academicWork.setCol1("");
                            academicWork.setCol2("");
                            academicWorksService.saveOrUpdate(academicWork);
                        }
                    }
                    if (thirdPage.getDeleteItems() != null) {
                        // 获取论文项 删除论文 删除的类型
                        for (DeleteItem item : thirdPage.getDeleteItems()) {
                            if (item.getDeleteType() == 4 && item.getDeleteId() != -1) {
                                // 根据id删除
                                academicWorksService.removeById(item.getDeleteId());
                                deleteFileService.delFile(item.getDeletePath(), request);
                            }
                        }
                    }
                    QueryWrapper<AcademicWorks> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("tutor_id", tutorId).eq("apply_id", applyId);
                    thirdPageOne.setAcademicWorks(academicWorksService.list(queryWrapper));
                } catch (Exception e) {
                    throw new RuntimeException("教材或学术著作填写错误，请检查" + "!" + e.getMessage());
                }
                break;
            case 5:
                // 设置科研教学奖励
                try {
                    if (thirdPage.getTeachingAwards() != null) {
                        for (TeachingAwards teachingAward : thirdPage.getTeachingAwards()) {
                            teachingAward.setApplyId(applyId);
                            teachingAward.setTutorId(tutorId);
                            teachingAward.setCol1("");
                            teachingAward.setCol2("");
                            System.out.println(teachingAward);
                            teachingAwardsService.saveOrUpdate(teachingAward);
                        }
                    }
                    if (thirdPage.getDeleteItems() != null) {
                        //获取论文项 删除论文 删除的类型
                        for (DeleteItem item : thirdPage.getDeleteItems()) {
                            if (item.getDeleteType() == 5 && item.getDeleteId() != -1) {
                                //根据id删除
                                teachingAwardsService.removeById(item.getDeleteId());
                                deleteFileService.delFile(item.getDeletePath(), request);
                            }
                        }
                    }
                    QueryWrapper<TeachingAwards> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("tutor_id", tutorId).eq("apply_id", applyId);
                    thirdPageOne.setTeachingAwards(teachingAwardsService.list(queryWrapper));
                } catch (Exception e) {
                    throw new RuntimeException("科研教学奖励填写错误，请检查" + "!" + e.getMessage());
                }
                break;
            case 6:
                // 设置发明专利
                try {
                    if (thirdPage.getInventionPatents() != null) {
                        for (InventionPatent inventionPatent : thirdPage.getInventionPatents()) {
                            inventionPatent.setApplyId(applyId);
                            inventionPatent.setTutorId(tutorId);
                            inventionPatent.setCol1("");
                            inventionPatent.setCol2("");
                            inventionPatentService.saveOrUpdate(inventionPatent);
                        }
                    }
                    if (thirdPage.getDeleteItems() != null) {
                        // 获取论文项 删除论文 删除的类型
                        for (DeleteItem item : thirdPage.getDeleteItems()) {
                            if (item.getDeleteType() == 6 && item.getDeleteId() != -1) {
                                // 根据id删除
                                inventionPatentService.removeById(item.getDeleteId());
                                deleteFileService.delFile(item.getDeletePath(), request);
                            }
                        }
                    }
                    QueryWrapper<InventionPatent> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("tutor_id", tutorId).eq("apply_id", applyId);
                    thirdPageOne.setInventionPatents(inventionPatentService.list(queryWrapper));
                } catch (Exception e) {
                    throw new RuntimeException("发明专利填写错误，请检查" + "!" + e.getMessage());
                }
                break;
//            case 7:
//                // 设置成果汇总
//                try {
//                    Summary summary = thirdPage.getSummary();
//                    summary.setApplyId(applyId);
//                    summary.setTutorId(tutorId);
//                    summaryService.saveOrUpdate(summary);
//
//                } catch (Exception e) {
//                    throw new RuntimeException("成果汇总填写错误，请检查" + "!" + e.getMessage());
//                }
//                break;

        }
        return thirdPageOne;
    }

    @Override
    public ThirdPage getThirdPage(int applyId, String tutorId) {

        ThirdPage thirdPage = PageInit.getThirdPage();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("apply_id", applyId);
        queryWrapper.eq("tutor_id", tutorId);

        try {
            // 获取学术论文
            List<AcademicPaper> academicPapers = academicPaperService.list(queryWrapper);
            thirdPage.setAcademicPapers(academicPapers);

            // 获取科研项目
            List<ResearchProject> researchProjects = researchProjectService.list(queryWrapper);
            thirdPage.setResearchProjects(researchProjects);

            // 获取教材或学术著作
            List<AcademicWorks> academicWorks = academicWorksService.list(queryWrapper);
            thirdPage.setAcademicWorks(academicWorks);

            // 获取科研教学奖励
            List<TeachingAwards> teachingAwards = teachingAwardsService.list(queryWrapper);
            thirdPage.setTeachingAwards(teachingAwards);

            // 获取发明专利
            List<InventionPatent> inventionPatents = inventionPatentService.list(queryWrapper);
            thirdPage.setInventionPatents(inventionPatents);

            // 获取汇总信息,需要用户手动点击汇总信息，所以不查询数据库
            Summary summary = summaryService.getOne(queryWrapper);

            if (summary == null) {
                thirdPage.setSummary(PageInit.getSummary());
            } else {
                thirdPage.setSummary(summary);
            }

        } catch (Exception e) {
            // 出现异常则返回空信息
            return PageInit.getThirdPage();
        }
        return thirdPage;
    }
}
