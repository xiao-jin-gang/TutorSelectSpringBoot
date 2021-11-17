package com.nwu.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nwu.entities.*;
import com.nwu.entities.PdfEntity.PdfTable;
import com.nwu.entities.PdfEntity.PdfTutorInspect;
import com.nwu.entities.tutor.childSubject.CourseTeaching;
import com.nwu.entities.tutor.childSubject.ExpertTitle;
import com.nwu.entities.tutor.childSubject.GroupsOrPartTimeJob;
import com.nwu.entities.tutor.childSubject.GuidingStudent;
import com.nwu.results.Result;
import com.nwu.results.ResultCode;
import com.nwu.service.PdfInspectService;
import com.nwu.service.TutorInspectService;
import com.nwu.service.scientificResearchManager.*;
import com.nwu.service.tutor.SummaryService;
import com.nwu.service.tutor.common.CourseTeachingService;
import com.nwu.service.tutor.common.GuidingStudentService;
import com.nwu.util.DataUtils;
import com.nwu.util.PDFTemplates;
import com.nwu.util.TimeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

@Service
public class PdfInspectServiceImpl implements PdfInspectService {

    @Resource
    TutorInspectService tutorInspectService;
    //学术论文
    @Resource
    AcademicPaperService academicPaperService;
    //论文汇总
    @Resource
    SummaryService summaryService;
    //科研项目
    @Resource
    ResearchProjectService researchProjectService;
    //教材或学术著作
    @Resource
    AcademicWorksService academicWorksService;
    //科研教学奖励
    @Resource
    TeachingAwardsService teachingAwardsService;
    //发明专利
    @Resource
    InventionPatentService inventionPatentService;
    //指导学生
    @Resource
    GuidingStudentService guidingStudentService;
    //课程教学情况
    @Resource
    CourseTeachingService courseTeachingService;

//    @Value("${PdfPath}")
//    private String path;

    @Override
    public String getTutorInspectPdf(Integer applyId, Integer applyTypeId, String pdfTemplate, HttpServletRequest request) {

        try
        {   //尝试进行读取资源文件
            File pdfFile = ResourceUtils.getFile("classpath:"+ pdfTemplate);
            FileInputStream s = new FileInputStream(pdfFile);
            s.close();
            if (s == null){
                return "";  //模板资源不存在！
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        PdfTutorInspect pdfTutorInspect= null;
        try{
            pdfTutorInspect = tutorInspectService.getPdfTutorInspect(applyId);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //处理何时参加社会兼职
        List<GroupsOrPartTimeJob> groupsOrPartTimeJobsList = JSON.parseArray(pdfTutorInspect.getAcademicGroupsJobs(), GroupsOrPartTimeJob.class);
        String groupsOrPartTimeJob="";
        for (GroupsOrPartTimeJob group: groupsOrPartTimeJobsList) {
            groupsOrPartTimeJob = groupsOrPartTimeJob + group.getTime()+" "+group.getGroups() +" "+group.getJob()+" \n" ;
        }
        //处理何时获得专家称号
        List<ExpertTitle> expertTitleList = JSON.parseArray(pdfTutorInspect.getTitlesOfExpert(), ExpertTitle.class);
        String expertTitle ="";
        for (ExpertTitle item: expertTitleList) {
            expertTitle = expertTitle + item.getTime() +" " +item.getTitle() + "\n";
        }

        HashMap<String, Object> textFields = new HashMap<String, Object>();
        //基本信息
        textFields.put("year_now", Calendar.getInstance().get(Calendar.YEAR));//当前年份
        textFields.put("name", pdfTutorInspect.getName());
        textFields.put("tutorId", pdfTutorInspect.getTutorId());
        textFields.put("organizationName", pdfTutorInspect.getOrganizationName());
        textFields.put("gender", pdfTutorInspect.getGender());
        textFields.put("birthday", pdfTutorInspect.getBirthday());
        textFields.put("identity", pdfTutorInspect.getIdentity());
        textFields.put("phone", pdfTutorInspect.getPhone());
        textFields.put("email", pdfTutorInspect.getEmail());
        textFields.put("finalDegree", pdfTutorInspect.getFinalDegree());
        textFields.put("awardingUnitTime", pdfTutorInspect.getAwardingUnitTime());
        textFields.put("researchDirections", pdfTutorInspect.getResearchDirections());
        textFields.put("academicGroupsJobs", groupsOrPartTimeJob);  //学术团体
        textFields.put("titlesOfExpert", expertTitle);              //处理何时获得专家称号
        textFields.put("title", pdfTutorInspect.getTitle());
        textFields.put("evaluateTime", pdfTutorInspect.getEvaluateTime());

        //首次专硕，专硕增岗 现任专业技术职位
        textFields.put("professionalTitle", pdfTutorInspect.getTitle());

        // 外单位调入填写 专硕增岗 学硕增岗 博导增岗
        textFields.put("sourceUnitName", pdfTutorInspect.getSourceUnitName());
        textFields.put("transferInTime", pdfTutorInspect.getTransferInTime());

        //首次博导
        if (applyTypeId == 1){
            textFields.put("firstDoctoralFirstTimeTutor", pdfTutorInspect.getFirstDoctoralFirstTimeTutor());
            textFields.put("firstDoctoralFirstDiscipline", pdfTutorInspect.getFirstDoctoralFirstDiscipline());
        }

        //博导增岗
        if (applyTypeId ==2){
            textFields.put("doctoralTutorOnDuty", pdfTutorInspect.getDoctoralTutorOnDuty());
            textFields.put("doctoralStartTime", pdfTutorInspect.getDoctoralStartTime());
        }

        //学硕增岗
        if (applyTypeId == 5){
            textFields.put("masterDegreeIncreaseTutorOnDuty", pdfTutorInspect.getMasterDegreeIncreaseTutorOnDuty());
            textFields.put("masterDegreeIncreaseTutorStartTime", pdfTutorInspect.getMasterDegreeIncreaseTutorStartTime());
        }
        //专硕增岗
        if (applyTypeId == 8){
            textFields.put("professionalMasterOnDuty", pdfTutorInspect.getProfessionalMasterOnDuty());
            textFields.put("professionalMasterStartTime", pdfTutorInspect.getProfessionalMasterStartTime());
        }

        // 博导，学硕的公共部分 申请学科代码
        if (applyTypeId == 1 || applyTypeId == 2 || applyTypeId == 4 || applyTypeId == 5){
            textFields.put("doctoralMasterApplicationSubjectUnit", pdfTutorInspect.getDoctoralMasterApplicationSubjectUnit());
            textFields.put("doctoralMasterSubjectCode", pdfTutorInspect.getDoctoralMasterSubjectCode());
            textFields.put("doctoralMasterSubjectName", pdfTutorInspect.getDoctoralMasterSubjectName());
        }

        //专硕公共部分
        if (applyTypeId == 7 || applyTypeId ==8){
            textFields.put("professionalApplicationSubjectUnit", pdfTutorInspect.getProfessionalApplicationSubjectUnit());
            textFields.put("professionalApplicationSubjectCode", pdfTutorInspect.getProfessionalApplicationSubjectCode());
            textFields.put("professionalApplicationSubjectName", pdfTutorInspect.getProfessionalApplicationSubjectName());
            textFields.put("professionalFieldCode", pdfTutorInspect.getProfessionalFieldCode());
            textFields.put("professionalFieldName", pdfTutorInspect.getProfessionalFieldName());
        }

        //图片信息 单独定位
        HashMap<String, Object> imgFields = new HashMap<>();
        if (pdfTutorInspect.getImage() != null) {
            imgFields.put("image", pdfTutorInspect.getImage());
        }
        else{
            imgFields.put("image",null);
        }

        //======================summary=====================
        QueryWrapper<Summary> wrapper = new QueryWrapper<>();
        wrapper.eq("apply_id", applyId).last("LIMIT 1");
        Summary one = summaryService.getOne(wrapper);
        textFields.put("firstAuthorPaper", one.getFirstAuthorPaper());
        textFields.put("authorityAmount", one.getAuthorityAmount());
        textFields.put("eiAmount", one.getEiAmount());
        textFields.put("cssciAmount", one.getCssciAmount());
        textFields.put("ssciAmount", one.getSsciAmount());
        textFields.put("ahciAmount",one.getAhciAmount());
        textFields.put("cscdAmount",one.getCscdAmount());
        textFields.put("cpciAmount",one.getCpciAmount());
        textFields.put("directProject", one.getDirectProject());
        textFields.put("projectNationalLevel", one.getProjectNationalLevel());
        textFields.put("projectProvinceLevel", one.getProjectProvinceLevel());
        textFields.put("accumulatedFunds", one.getAccumulatedFunds());
        textFields.put("horizontalProject", one.getHorizontalProject());
        textFields.put("publishWorks", one.getPublishWorks());
        textFields.put("publishWorksWords", one.getPublishWorksWords());
        textFields.put("scientificAwards", one.getScientificAwards());
        textFields.put("awardsNationalLevel", one.getAwardsNationalLevel());
        textFields.put("awardsProvinceLevel", one.getAwardsProvinceLevel());
        textFields.put("inventionPatentAmount", one.getInventionPatentAmount());
        textFields.put("newUtilityPatent", one.getNewUtilityPatent());

        //=======================学术论文表格=====================
        // { label: '文史', value: 1 },
        // { label: '理工', value: 2 },
        // { label: '交叉学科', value: 3 }
        PdfTable tableAcademicPaper_lg = new PdfTable();      //理工
        PdfTable tableAcademicPaper_ws = new PdfTable();        //文史
        if (pdfTutorInspect.getSubject() == 2 || pdfTutorInspect.getSubject() == 3) {
            tableAcademicPaper_lg.setColNames("序号,名称,第一\n作者,通讯\n作者,发表时间,期刊名称,期刊类别,分区/\n影响因子");
            tableAcademicPaper_lg.setColFields("id,paperName,firstAuthorName,communicationAuthorName,paperPublicationTime,journalName,journalCategory,sciPart");
            //根据主键id去查询论文表
            QueryWrapper<AcademicPaper> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("apply_id", applyId);
            List<AcademicPaper> academicPaperList = academicPaperService.list(queryWrapper);
            ArrayList<Map<String, Object>> academicPaperPdfList = new ArrayList<>();
            for (int i = 0; i < academicPaperList.size(); i++) {
                HashMap<String, Object> row = new HashMap<String, Object>();
                row.put("id", i + 1);
                row.put("paperName", academicPaperList.get(i).getPaperName());
                row.put("firstAuthorName", academicPaperList.get(i).getFirstAuthorName());
                row.put("communicationAuthorName", academicPaperList.get(i).getCommunicationAuthorName());
                row.put("paperPublicationTime", academicPaperList.get(i).getPaperPublicationTime());
                row.put("journalName", academicPaperList.get(i).getJournalName());
                row.put("journalCategory", academicPaperList.get(i).getJournalCategory());
                if (academicPaperList.get(i).getSciPart() != null && String.valueOf(academicPaperList.get(i).getImpactFactors()) != null) {
                    row.put("sciPart", academicPaperList.get(i).getSciPart() + "/" + academicPaperList.get(i).getImpactFactors());
                } else if (String.valueOf(academicPaperList.get(i).getImpactFactors()) == null) {
                    row.put("sciPart", academicPaperList.get(i).getImpactFactors());
                } else {
                    row.put("sciPart", academicPaperList.get(i).getSciPart());
                }
                academicPaperPdfList.add(row);
            }
            //存到对象中 pdf
            tableAcademicPaper_lg.setDataList(academicPaperPdfList);
        } else {
            //文科类 比之前少一个
            tableAcademicPaper_ws.setColNames("序号,论文名称,第一作者,发表时间,期刊名称,期刊等级");
            tableAcademicPaper_ws.setColFields("id,paperName,firstAuthorName,paperPublicationTime,journalName,journalLevel");
            ArrayList<Map<String, Object>> academicPaperPdfList = new ArrayList<>();
            QueryWrapper<AcademicPaper> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("apply_id", applyId);
            List<AcademicPaper> academicPaperList = academicPaperService.list(queryWrapper);    //从数据库中查询出论文
            for (int i = 0; i < academicPaperList.size(); i++) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("id", i + 1);
                map.put("paperName", academicPaperList.get(i).getPaperName());
                map.put("firstAuthorName", academicPaperList.get(i).getFirstAuthorName());
                map.put("paperPublicationTime", academicPaperList.get(i).getPaperPublicationTime());
                map.put("journalName", academicPaperList.get(i).getJournalName());
                map.put("journalLevel", academicPaperList.get(i).getJournalLevel());
                academicPaperPdfList.add(map);
            }
            tableAcademicPaper_ws.setDataList(academicPaperPdfList);
        }

        //=================科研项目=============9
        PdfTable tableResearchProject = new PdfTable();
        tableResearchProject.setColNames("序号,名称,批准号,负责人,开始时间,结题时间,项目级别,项目分类,总经费/\n（万元）");
        tableResearchProject.setColFields("id,projectName,projectNumber,projectChargeName,projectStartTime,projectEndTime,projectLevel,projectCategory,projectTotalPrice");
        //从数据库中查询出论文
        QueryWrapper<ResearchProject> RPqueryWrapper = new QueryWrapper<>();
        RPqueryWrapper.eq("apply_id", applyId);
        List<ResearchProject> researchProjectList = researchProjectService.list(RPqueryWrapper);
        ArrayList<Map<String, Object>> researchProjectPdfList = new ArrayList<>();
        //遍历科研项目
        for (int i = 0; i < researchProjectList.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", i + 1);
            map.put("projectName", researchProjectList.get(i).getProjectName());
            map.put("projectNumber", researchProjectList.get(i).getApprovalNumber());       //有改动
            map.put("projectChargeName", researchProjectList.get(i).getProjectChargeName());
            map.put("projectStartTime", researchProjectList.get(i).getProjectStartTime());
            map.put("projectEndTime", researchProjectList.get(i).getProjectEndTime());
            map.put("projectLevel", researchProjectList.get(i).getProjectLevel());
            map.put("projectCategory", researchProjectList.get(i).getProjectCategory());
            map.put("projectTotalPrice", researchProjectList.get(i).getProjectTotalPrice());
            researchProjectPdfList.add(map);
        }
        tableResearchProject.setDataList(researchProjectPdfList);   //设置到实体类中

        //=================教材或学术著作AcademicWorks=============7
        PdfTable tableAcademicWorks = new PdfTable();
        tableAcademicWorks.setColNames("序号,名称,编号,出版时间,出版单位,作者姓名,完成字数（万字）");
        tableAcademicWorks.setColFields("id,worksName,worksNumber,worksPublicationTime,worksPublicationUnit,authorName,totalWords");
        //设置datalist
        QueryWrapper<AcademicWorks> awQueryWrapper = new QueryWrapper<>();
        awQueryWrapper.eq("apply_id", applyId);
        List<AcademicWorks> academicWorkList = academicWorksService.list(awQueryWrapper);
        //声明一个arrayList数组 用于放入DataList数组中
        ArrayList<Map<String, Object>> academicWorkPdfList = new ArrayList<>();
        for (int i = 0; i < academicWorkList.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", i + 1);
            map.put("worksName", academicWorkList.get(i).getWorksName());
            map.put("worksNumber", academicWorkList.get(i).getWorksNumber());
            map.put("worksPublicationTime", academicWorkList.get(i).getWorksPublicationTime());
            map.put("worksPublicationUnit", academicWorkList.get(i).getWorksPublicationUnit());
            map.put("authorName", academicWorkList.get(i).getAuthorName());
            map.put("totalWords", academicWorkList.get(i).getTotalWords());
            academicWorkPdfList.add(map);
        }
        tableAcademicWorks.setDataList(academicWorkPdfList);

        //=================科研教学奖励============================ 7=7
        PdfTable tableTeachingAwards = new PdfTable();
        tableTeachingAwards.setColNames("序号,名称,排名,颁奖单位,获奖级别,获奖人姓名,获奖时间");
        tableTeachingAwards.setColFields("id,awardsName,awardsRank,awardsUnit,awardsLevel,awardsAuthorName,awardsTime");
        QueryWrapper<TeachingAwards> taQueryWrapper = new QueryWrapper<>();
        taQueryWrapper.eq("apply_id", applyId);
        List<TeachingAwards> teachingAwardsList = teachingAwardsService.list(taQueryWrapper);
        //声明一个arrayList数组 用于放入DataList数组中
        ArrayList<Map<String, Object>> teachingAwardsPdfList = new ArrayList<>();
        for (int i = 0; i < teachingAwardsList.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", i + 1);
            map.put("awardsName", teachingAwardsList.get(i).getAwardsName());
            map.put("awardsRank", teachingAwardsList.get(i).getAwardsRank());
            map.put("awardsUnit", teachingAwardsList.get(i).getAwardsUnit());
            map.put("awardsLevel", teachingAwardsList.get(i).getAwardsLevel());
            map.put("awardsAuthorName", teachingAwardsList.get(i).getAwardsAuthorName());
            map.put("awardsTime", teachingAwardsList.get(i).getAwardsTime());
            teachingAwardsPdfList.add(map);
        }
        tableTeachingAwards.setDataList(teachingAwardsPdfList);

        //====================发明专利================= 6
        PdfTable tableInventionPatent = new PdfTable();
        tableInventionPatent.setColNames("序号,专利名称,作者,授权（颁证）时间,专利授权号,专利类型");
        tableInventionPatent.setColFields("id,patentName,patentAuthorName,patentGrantTime,patentGrantNumber,patentType");
        QueryWrapper<InventionPatent> ipQueryWrapper = new QueryWrapper<>();
        ipQueryWrapper.eq("apply_id", applyId);
        List<InventionPatent> inventionPatentList = inventionPatentService.list(ipQueryWrapper);
        //声明一个arrayList数组 用于放入DataList数组中
        ArrayList<Map<String, Object>> inventionPatentPdfList = new ArrayList<>();
        for (int i = 0; i < inventionPatentList.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", i + 1);
            map.put("patentName", inventionPatentList.get(i).getPatentName());
            map.put("patentAuthorName", inventionPatentList.get(i).getPatentAuthorName());
            map.put("patentGrantTime", inventionPatentList.get(i).getPatentGrantTime());
            map.put("patentGrantNumber", inventionPatentList.get(i).getPatentGrantNumber());
            map.put("patentType", inventionPatentList.get(i).getPatentType());
            inventionPatentPdfList.add(map);
        }
        tableInventionPatent.setDataList(inventionPatentPdfList);

        //=====================指导博士/硕士/学士==================
        //首次博士：协助指导博士 指导硕士 1
        //博士增岗：指导博士 指导硕士    2
        //首次专硕：协助指导硕士 指导本科  4
        //首次学硕：协助指导硕士 指导本科
        //学硕增岗：指导硕士   5
        //专硕增岗：指导硕士   8

        //指导博士
        PdfTable tableGuidingDoctor = new PdfTable();
        tableGuidingDoctor.setColNames("学生姓名,论文题目,入学时间,是否获得学位");
        tableGuidingDoctor.setColFields("studentName,degreePaperTitle,studentEntryTime,isGainDegree");
        //协助指导博士
        PdfTable tableHelpGuidingDoctor = new PdfTable();
        tableHelpGuidingDoctor.setColNames("学生姓名,论文题目,入学时间,指导教师,是否获得学位");
        tableHelpGuidingDoctor.setColFields("studentName,degreePaperTitle,studentEntryTime,tutorName,isGainDegree");
        //指导硕士
        PdfTable tableGuidingMaster = new PdfTable();
        tableGuidingMaster.setColNames("学生姓名,论文题目,入学时间,是否获得学位");
        tableGuidingMaster.setColFields("studentName,degreePaperTitle,studentEntryTime,isGainDegree");
        //协助指导硕士
        PdfTable tableHelpGuidingMaster = new PdfTable();
        tableHelpGuidingMaster.setColNames("学生姓名,论文题目,入学时间,指导教师,是否获得学位");
        tableHelpGuidingMaster.setColFields("studentName,degreePaperTitle,studentEntryTime,tutorName,isGainDegree");
        //指导本科
        PdfTable tableGuidingBachelor = new PdfTable();
        tableGuidingBachelor.setColNames("学生姓名,毕业时间,毕业设计题目");
        tableGuidingBachelor.setColFields("studentName,graduateTime,degreePaperTitle");

        QueryWrapper<GuidingStudent> gsQueryWrapper = new QueryWrapper<>();
        gsQueryWrapper.eq("apply_id", applyId);
        List<GuidingStudent> guidingStudentList = guidingStudentService.list(gsQueryWrapper);
        //去查询是什么增岗 applyTypeId
        ArrayList<Map<String, Object>> guidingDoctorPdfList = new ArrayList<>();            //博士
        ArrayList<Map<String, Object>> guidingMasterPdfList = new ArrayList<>();            //硕士
        ArrayList<Map<String, Object>> guidingHelpDoctorPdfList = new ArrayList<>();        //协助指导博士
        ArrayList<Map<String, Object>> guidingHelpMasterPdfList = new ArrayList<>();        //协助指导硕士
        ArrayList<Map<String, Object>> guidingBachelorPdfList = new ArrayList<>();          //指导本科
        switch (applyTypeId) {
            case 1: //首次博士    协助指导博士 指导硕士  studentName,degreePaperTitle,studentEntryTime,tutorName,isGainDegree
                for (int i = 0; i < guidingStudentList.size(); i++) {
                    if ("博士".equals(guidingStudentList.get(i).getStudentType())) {    //协助指导博士
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("studentName", guidingStudentList.get(i).getStudentName());
                        map.put("degreePaperTitle", guidingStudentList.get(i).getDegreePaperTitle());
                        map.put("studentEntryTime", guidingStudentList.get(i).getStudentEntryTime());
                        map.put("tutorName", guidingStudentList.get(i).getTutorName());
                        map.put("isGainDegree", guidingStudentList.get(i).getIsGainDegree());
                        guidingHelpDoctorPdfList.add(map);
                    } else { //指导硕士 studentName,degreePaperTitle,studentEntryTime,isGainDegree
                        HashMap<String, Object> row = new HashMap<>();
                        row.put("studentName", guidingStudentList.get(i).getStudentName());
                        row.put("degreePaperTitle", guidingStudentList.get(i).getDegreePaperTitle());
                        row.put("studentEntryTime", guidingStudentList.get(i).getStudentEntryTime());
                        row.put("isGainDegree", guidingStudentList.get(i).getIsGainDegree());
                        guidingMasterPdfList.add(row);
                    }
                }
                tableHelpGuidingDoctor.setDataList(guidingHelpDoctorPdfList);
                tableGuidingMaster.setDataList(guidingMasterPdfList);
                break;
            case 2: //博导增岗
                for (int i = 0; i < guidingStudentList.size(); i++) {
                    if ("博士".equals(guidingStudentList.get(i).getStudentType())) {    //指导博士 studentName,degreePaperTitle,studentEntryTime,isGainDegree
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("studentName", guidingStudentList.get(i).getStudentName());
                        map.put("degreePaperTitle", guidingStudentList.get(i).getDegreePaperTitle());
                        map.put("studentEntryTime", guidingStudentList.get(i).getStudentEntryTime());
                        map.put("isGainDegree", guidingStudentList.get(i).getIsGainDegree());
                        guidingDoctorPdfList.add(map);
                    } else { //指导硕士 studentName,degreePaperTitle,studentEntryTime,isGainDegree
                        HashMap<String, Object> row = new HashMap<>();
                        row.put("studentName", guidingStudentList.get(i).getStudentName());
                        row.put("degreePaperTitle", guidingStudentList.get(i).getDegreePaperTitle());
                        row.put("studentEntryTime", guidingStudentList.get(i).getStudentEntryTime());
                        row.put("isGainDegree", guidingStudentList.get(i).getIsGainDegree());
                        guidingMasterPdfList.add(row);
                    }
                }
                tableGuidingDoctor.setDataList(guidingDoctorPdfList);
                tableGuidingMaster.setDataList(guidingMasterPdfList);
                break;
            case 4: //首次学硕
            case 7: //首次专硕
                for (int i = 0; i < guidingStudentList.size(); i++) {
                    if ("硕士".equals(guidingStudentList.get(i).getStudentType())) {    //协助指导硕士 studentName,degreePaperTitle,studentEntryTime,tutorName,isGainDegree
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("studentName", guidingStudentList.get(i).getStudentName());
                        map.put("degreePaperTitle", guidingStudentList.get(i).getDegreePaperTitle());
                        map.put("studentEntryTime", guidingStudentList.get(i).getStudentEntryTime());
                        map.put("tutorName", guidingStudentList.get(i).getTutorName());
                        map.put("isGainDegree", guidingStudentList.get(i).getIsGainDegree());
                        guidingHelpMasterPdfList.add(map);
                    } else { //指导本科 studentName,graduateTime,degreePaperTitle
                        HashMap<String, Object> row = new HashMap<>();
                        row.put("studentName", guidingStudentList.get(i).getStudentName());
                        row.put("degreePaperTitle", guidingStudentList.get(i).getDegreePaperTitle());
                        row.put("graduateTime", guidingStudentList.get(i).getGraduateTime());
                        guidingBachelorPdfList.add(row);
                    }
                }
                tableHelpGuidingMaster.setDataList(guidingHelpMasterPdfList);
                tableGuidingBachelor.setDataList(guidingBachelorPdfList);
                break;
            case 5: //学硕增岗
            case 8:
                for (int i = 0; i < guidingStudentList.size(); i++) {
                    //指导硕士
                    HashMap<String, Object> row = new HashMap<>();
                    row.put("studentName", guidingStudentList.get(i).getStudentName());
                    row.put("degreePaperTitle", guidingStudentList.get(i).getDegreePaperTitle());
                    row.put("studentEntryTime", guidingStudentList.get(i).getStudentEntryTime());
                    row.put("isGainDegree", guidingStudentList.get(i).getIsGainDegree());
                    guidingMasterPdfList.add(row);
                }
                tableGuidingMaster.setDataList(guidingMasterPdfList);
                break;

        }

        //==================研究生课程教学情况======================
        PdfTable tableCourseTeaching = new PdfTable();
        tableCourseTeaching.setColNames("课程名称,课程时间,授课总课时,授课对象");
        tableCourseTeaching.setColFields("courseName,courseTime,courseDuration,courseObject");
        QueryWrapper<CourseTeaching> ctQueryWrapper = new QueryWrapper<>();
        ctQueryWrapper.eq("apply_id", applyId);
        List<CourseTeaching> courseTeachingList = courseTeachingService.list(ctQueryWrapper);
        ArrayList<Map<String, Object>> courseTeachingPdfList = new ArrayList<>();
        for (int i = 0; i < courseTeachingList.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("courseName", courseTeachingList.get(i).getCourseName());
            map.put("courseTime", courseTeachingList.get(i).getCourseTime());
            map.put("courseDuration", courseTeachingList.get(i).getCourseDuration());
            map.put("courseObject", courseTeachingList.get(i).getCourseObject());
            courseTeachingPdfList.add(map);
        }
        tableCourseTeaching.setDataList(courseTeachingPdfList);

        //创建表格内table所需字段
        HashMap<String, PdfTable> tableFields = new HashMap<>();
        tableFields.put("tableAcademicPaper_lg", tableAcademicPaper_lg);    //理工 交叉
        tableFields.put("tableAcademicPaper_ws", tableAcademicPaper_ws);    //文史
        tableFields.put("tableResearchProject",tableResearchProject);       //科研项目
        tableFields.put("tableAcademicWorks",tableAcademicWorks);           //教材或学术著作AcademicWorks
        tableFields.put("tableTeachingAwards",tableTeachingAwards);         //科研教学奖励
        tableFields.put("tableInventionPatent",tableInventionPatent);       //发明专利
        tableFields.put("tableGuidingDoctor",tableGuidingDoctor);           //指导博士
        tableFields.put("tableHelpGuidingDoctor",tableHelpGuidingDoctor);   //协助指导博士
        tableFields.put("tableGuidingMaster",tableGuidingMaster);           //指导硕士
        tableFields.put("tableHelpGuidingMaster",tableHelpGuidingMaster);   //协助指导硕士
        tableFields.put("tableGuidingBachelor",tableGuidingBachelor);       //指导本科
        tableFields.put("tableCourseTeaching",tableCourseTeaching);         //研究生课程教学情况

        //创建pdf生成路径
        try{
//            String path="D:\\RARZIP\\PDF\\";
            String path = DataUtils.pdfPath;

            String pdfName = pdfTutorInspect.getName();
            switch (applyTypeId){
                case 1: pdfName = pdfName + "首次博导表"; break;
                case 2: pdfName = pdfName + "博导增岗表"; break;
                case 4: pdfName = pdfName + "首次学硕表"; break;
                case 5: pdfName = pdfName + "学硕增岗表"; break;
                case 7: pdfName = pdfName + "首次专硕表"; break;
                case 8: pdfName = pdfName + "专硕增岗表"; break;
            }
            pdfName = pdfName +".pdf";   //pdf名称
            path = path + pdfName;                                          //物理路径
            File file = new File(path);
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            if (file.exists()){
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            new PDFTemplates(pdfTemplate).export(fileOutputStream,textFields,tableFields,imgFields);
            String httpPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/downFile/PDF/" + pdfName;
            System.out.println(httpPath);
            return httpPath;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
