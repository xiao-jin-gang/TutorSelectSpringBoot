package com.nwu.service.admin.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.entities.Apply;
import com.nwu.entities.tutor.*;
import com.nwu.entities.tutor.FirstPage;
import com.nwu.entities.tutor.NoSecondPage;
import com.nwu.mapper.admin.ApplyMapper;
import com.nwu.service.TutorInspectService;
import com.nwu.service.admin.ApplyService;
import com.nwu.service.tutor.common.FourthService;
import com.nwu.service.tutor.common.SecondService;
import com.nwu.service.tutor.common.ThirdService;
import com.nwu.service.tutor.noInspectApply.NoFirstService;
import com.nwu.service.tutor.noInspectApply.NoSecondService;
import com.nwu.vo.ApplyDisplayVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements ApplyService {

    @Resource
    private ApplyMapper applyMapper;

    @Resource
    private TutorInspectService tutorInspectService;

    @Resource
    private SecondService secondService;

    @Resource
    private ThirdService thirdService;

    @Resource
    private FourthService fourthService;

    @Resource
    private NoFirstService noFirstService;

    @Resource
    private NoSecondService noSecondService;

    @Override
    public int updateApplyStatusAndTime(Integer applyId, Integer status, String time) {
        return applyMapper.updateApplyStatusAndTime(applyId, status, time);
    }

    @Override
    public int updateApplyStatus(Integer applyId, Integer status, String commit) {
        applyMapper.updateApplyStatusAndCommit(applyId, status, commit);
        return 1;
    }

    /**
     * 社科处更新 备注，状态码
     * @param applyId 申请表的 id 值
     * @param status  修改后的状态
     * @param commit  备注，可选
     * @return
     */
    @Override
    public int updateSocialApplyStatus(Integer applyId, Integer status, String commit) {
        applyMapper.updateSocialApplyStatusAndCommit(applyId, status, commit);
        return 1;
    }

    //院系秘书初审提交分会页面的更新操作
    @Override
    public int updateApplyStatusSfh(Integer applyId, Integer status, String commit) {
        applyMapper.updateApplyStatusAndCommitSfh(applyId, status, commit);
        return 1;
    }

    @Override
    public int updateApplyStatusAndCommitXy(Integer applyId, Integer status, String commit) {
        applyMapper.updateApplyStatusAndCommitXy(applyId, status, commit);
        return 1;
    }

    @Override
    public List<ApplyDisplay> getApplyList(String tutorId) {

        List<ApplyDisplay> applyList = new ArrayList<>();

        // 获取非免审的申请
        List<ApplyDisplayVo> inspectApply = applyMapper.getInspectApply(tutorId);

        if (inspectApply != null) {
            for (ApplyDisplayVo display : inspectApply) {
                ApplyDisplay applyDisplay = new ApplyDisplay();

                // 设置基本信息
                applyDisplay.setApplyId(display.getApplyId());
                applyDisplay.setTutorId(display.getTutorId());
                applyDisplay.setApplyTypeId(display.getApplyTypeId());
                applyDisplay.setApplyName(display.getApplyName());
                applyDisplay.setStatus(display.getStatus());
                applyDisplay.setStatusDisplay(display.getStatusDisplay());
                applyDisplay.setCommitGraduate(display.getCommitGraduate());
                applyDisplay.setCommitSocial(display.getCommitSocial());
                applyDisplay.setCommit(display.getCommit());

                // 填写申请的专业信息
                switch (display.getApplyTypeId()) {
                    case 1:
                    case 2:
                    case 4:
                    case 5:
                        applyDisplay.setApplyDepartment(display.getDoctoralMasterApplicationSubjectUnit());
                        if (display.getDoctoralMasterSubjectCode() == null) {
                            applyDisplay.setApplySubject("");
                        } else {
                            applyDisplay.setApplySubject(display.getDoctoralMasterSubjectCode() + " " + display.getDoctoralMasterSubjectName());
                        }
                        break;
                    case 7:
                    case 8:
                        applyDisplay.setApplyDepartment(display.getProfessionalApplicationSubjectUnit());
                        if (display.getProfessionalApplicationSubjectCode() == null) {
                            applyDisplay.setApplySubject("");
                        } else {
                            if (display.getProfessionalFieldCode() == null) {
                                applyDisplay.setApplySubject(display.getProfessionalApplicationSubjectCode() + " " + display.getProfessionalApplicationSubjectName());
                            } else {
                                applyDisplay.setApplySubject(display.getProfessionalFieldCode() + " " + display.getProfessionalFieldName());
                            }
                        }
                        break;
                    default:
                        break;
                }
                // 标识非免审
                applyDisplay.setNoInspect(false);
                applyList.add(applyDisplay);
            }
        }


        // 获取免审的申请
        List<ApplyDisplayVo> noInspectApply = applyMapper.getNoInspectApply(tutorId);

        if (noInspectApply != null) {
            for (ApplyDisplayVo display : noInspectApply) {

                ApplyDisplay applyDisplay = new ApplyDisplay();

                // 设置基本信息
                applyDisplay.setApplyId(display.getApplyId());
                applyDisplay.setTutorId(display.getTutorId());
                applyDisplay.setApplyTypeId(display.getApplyTypeId());
                applyDisplay.setApplyName(display.getApplyName());
                applyDisplay.setStatus(display.getStatus());
                applyDisplay.setStatusDisplay(display.getStatusDisplay());
                applyDisplay.setCommitGraduate(display.getCommitGraduate());
                applyDisplay.setCommitSocial(display.getCommitSocial());
                applyDisplay.setCommit(display.getCommit());


                switch (display.getApplyTypeId()) {
                    case 3:
                    case 6:
                        // 设置申请的学院和专业
                        if (!"".equals(display.getAppliedSubjectUnit()) && display.getAppliedSubjectUnit() != null) {
                            applyDisplay.setApplyDepartment(display.getAppliedSubjectUnit());
                        } else {
                            applyDisplay.setApplyDepartment("");
                        }

                        if (!"".equals(display.getAppliedSubjectCode()) && display.getAppliedSubjectCode() != null) {
                            applyDisplay.setApplySubject(display.getAppliedSubjectCode() + " " + display.getAppliedSubjectName());
                        } else {
                            applyDisplay.setApplySubject("");
                        }
                        break;
                    case 9:
                        applyDisplay.setApplyDepartment(display.getProfessionalApplicationSubjectUnit());
                        if (display.getProfessionalApplicationSubjectCode() == null) {
                            applyDisplay.setApplySubject("");
                        } else {
                            if (display.getProfessionalFieldCode() == null) {
                                applyDisplay.setApplySubject(display.getProfessionalApplicationSubjectCode() + " " + display.getProfessionalApplicationSubjectName());
                            } else {
                                applyDisplay.setApplySubject(display.getProfessionalFieldCode() + " " + display.getProfessionalFieldName());
                            }
                        }
                        break;
                    default:
                        break;
                }

                // 标识免审
                applyDisplay.setNoInspect(true);
                applyList.add(applyDisplay);
            }
        }

        // 返回两个列表合并
        return applyList;
    }

    @Override
    public ApplyDetails getApplyDetails(int applyId, int isInspect, String tutorId) {

        ApplyDetails details = new ApplyDetails();

        try {

            // 非免审
            if (isInspect == 1){
                // 获取第一页
                FirstPage firstPage = tutorInspectService.getFirstPage(applyId);
                details.setFirstPage(firstPage);

                // 获取第二页
                SecondPage secondPage = secondService.getSecondPage(applyId);
                details.setSecondPage(secondPage);

                // 获取第三页
                ThirdPage thirdPage = thirdService.getThirdPage(applyId, tutorId);
                details.setThirdPage(thirdPage);

                // 获取第四页
                FourthPage fourthPage = fourthService.getFourthPage(applyId, tutorId);
                details.setFourthPage(fourthPage);

            } else {
                // 获取免审第一页
                FirstPage noFirstPage = noFirstService.getNoFirstPage(applyId);
                details.setNoFirstPage(noFirstPage);

                // 获取免审第二页
                NoSecondPage noSecondPage = noSecondService.getSecondPage(applyId);
                details.setNoSecondPage(noSecondPage);
            }
        } catch (Exception e){
            throw new RuntimeException("获取信息失败，请稍后再试" + "!" + e.getMessage());
        }

        return details;
    }
    /***
    * @Param [] //参数
    * @description   //描述
    * @author dynamic //作者 管理员点击提价按钮修改状态服务
    * @return int  //返回值
    */
    public int updateApply399to39(){
        return applyMapper.updateApply399to39();
    }
    public int updateApply388to38(){
        return applyMapper.updateApply388to38();
    }

}
