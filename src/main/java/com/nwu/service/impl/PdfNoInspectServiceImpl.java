package com.nwu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.nwu.entities.PdfEntity.PdfNoTutorInspect;
import com.nwu.entities.PdfEntity.PdfTable;
import com.nwu.entities.tutor.childSubject.ExpertTitle;
import com.nwu.entities.tutor.childSubject.GroupsOrPartTimeJob;
import com.nwu.entities.tutor.childSubject.ResearchProject;
import com.nwu.entities.tutor.childSubject.TeachingAward;
import com.nwu.mapper.TutorNoInspectMapper;
import com.nwu.service.PdfNoInspectService;
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
public class PdfNoInspectServiceImpl implements PdfNoInspectService {

//    @Value("${PdfPath}")
//    private String path;

    @Resource
    TutorNoInspectMapper tutorNoInspectMapper;

    @Override
    public String  getTutorNoInspect(Integer applyId, Integer applyTypeId, String pdfTemplate, HttpServletRequest request) {

        try
        {   //尝试进行读取资源文件
            File pdfFile = ResourceUtils.getFile("classpath:"+ pdfTemplate);
            FileInputStream s = new FileInputStream(pdfFile);
            s.close();
            if (s == null){
                return "";   //模板资源不存在
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        PdfNoTutorInspect pdfTutorNoInspect = null;
        //获取免审数据库的数据
        try {
            pdfTutorNoInspect = tutorNoInspectMapper.getPdfTutorNoInspect(String.valueOf(applyId));
        }
        catch (Exception e){
            //输出异常信息
            e.printStackTrace();
        }
            //处理科研教学奖励
            HashMap<String, Object> textFields = new HashMap<>();
            //--------------------------基本信息及申请学科表单(免审)----------------------
            textFields.put("year_now", Calendar.getInstance().get(Calendar.YEAR));//当前年份
            textFields.put("name", pdfTutorNoInspect.getName());
            textFields.put("tutorId", pdfTutorNoInspect.getTutorId());
            textFields.put("organizationName", pdfTutorNoInspect.getOrganizationName());
            textFields.put("gender", pdfTutorNoInspect.getGender());
            textFields.put("birthday", pdfTutorNoInspect.getBirthday());
            textFields.put("identity", pdfTutorNoInspect.getIdentity());
            textFields.put("phone", pdfTutorNoInspect.getPhone());
            textFields.put("email", pdfTutorNoInspect.getEmail());
            textFields.put("finalDegree", pdfTutorNoInspect.getFinalDegree());
            textFields.put("awardingUnitTime", pdfTutorNoInspect.getAwardingUnitTime());
            textFields.put("researchDirections", pdfTutorNoInspect.getResearchDirections());
            textFields.put("title", pdfTutorNoInspect.getTitle());
            textFields.put("evaluateTime", pdfTutorNoInspect.getEvaluateTime());
            textFields.put("appliedSubjectUnit", pdfTutorNoInspect.getAppliedSubjectUnit());
            textFields.put("appliedSubjectCode", pdfTutorNoInspect.getAppliedSubjectCode());
            textFields.put("appliedSubjectName", pdfTutorNoInspect.getAppliedSubjectName());
            textFields.put("exemptionConditions", pdfTutorNoInspect.getExemptionConditions());

            if (applyTypeId == 3){
                /* 博导免审 */
                textFields.put("masterDirectorTime", pdfTutorNoInspect.getEvaluateTime());
                textFields.put("masterDirectorSubject", pdfTutorNoInspect.getEvaluateTime());
            }
            if (applyTypeId == 9){
                /* 专硕免审 */
                textFields.put("professionalApplicationSubjectUnit",pdfTutorNoInspect.getProfessionalApplicationSubjectUnit());
                textFields.put("professionalApplicationSubjectCode",pdfTutorNoInspect.getProfessionalApplicationSubjectCode());
                textFields.put("professionalApplicationSubjectName",pdfTutorNoInspect.getProfessionalApplicationSubjectName());
                textFields.put("professionalFieldCode",pdfTutorNoInspect.getProfessionalFieldCode());
                textFields.put("professionalFieldName",pdfTutorNoInspect.getProfessionalFieldName());
            }
            //图片处理
            HashMap<String, Object> imgFields = new HashMap<>();
            if (pdfTutorNoInspect.getImage() != null) {
                imgFields.put("image", pdfTutorNoInspect.getImage());
            }
            else
            {
                imgFields.put("image",null);
            }
            //科研项目处理
            PdfTable tableResearchProjects = new PdfTable();
            tableResearchProjects.setColNames("序号,项目名称,负责人,开始时间,项目级别,总经费\n(万元)");
            tableResearchProjects.setColFields("id,projectName,projectChargeName,projectStartTime,projectLevel,projectTotalPrice");
            List<ResearchProject> researchProjectList = JSON.parseArray(pdfTutorNoInspect.getResearchProjects(), ResearchProject.class);
            ArrayList<Map<String,Object>> researchProjectPdfList = new ArrayList<>();
            if(researchProjectList!=null){
                for (int i =0 ;i < researchProjectList.size(); i++){
                    HashMap<String , Object> map = new HashMap<>();
                    map.put("id",i+1);
                    map.put("projectName",researchProjectList.get(i).getProjectName());
                    map.put("projectChargeName",researchProjectList.get(i).getProjectChargeName());
                    map.put("projectStartTime",researchProjectList.get(i).getProjectStartTime());
                    map.put("projectLevel",researchProjectList.get(i).getProjectLevel());
                    map.put("projectTotalPrice",researchProjectList.get(i).getProjectTotalPrice());
                    researchProjectPdfList.add(map);
                }
            }

            tableResearchProjects.setDataList(researchProjectPdfList);
            //教学奖励处理
            PdfTable tableTeachingAwards = new PdfTable();
            tableTeachingAwards.setColNames("序号,奖励名称,颁奖单位,获奖级别,获奖日期,获奖人");
            tableTeachingAwards.setColFields("id,awardsName,awardsUnit,awardsLevel,awardsTime,awardsAuthorName");
            List<TeachingAward> teachingAwardList = JSON.parseArray(pdfTutorNoInspect.getTeachingAwards(), TeachingAward.class);
            ArrayList<Map<String,Object>> teachingAwardPdfList = new ArrayList<>();
            if (teachingAwardList!=null){
                for (int i =0 ;i < teachingAwardList.size(); i++){
                    HashMap<String , Object> map = new HashMap<>();
                    map.put("id",i+1);
                    map.put("awardsName",teachingAwardList.get(i).getAwardsName());
                    map.put("awardsUnit",teachingAwardList.get(i).getAwardsUnit());
                    map.put("awardsLevel",teachingAwardList.get(i).getAwardsLevel());
                    map.put("awardsTime",teachingAwardList.get(i).getAwardsTime());
                    map.put("awardsAuthorName",teachingAwardList.get(i).getAwardsAuthorName());
                    teachingAwardPdfList.add(map);
                }
            }
            tableTeachingAwards.setDataList(teachingAwardPdfList);

            HashMap<String, PdfTable> tableFields = new HashMap<>();
            tableFields.put("tableNoResearchProjects",tableResearchProjects);
            tableFields.put("tableNoTeachingAwards",tableTeachingAwards);

        //创建pdf生成路径
        try{
//            String path="D:\\RARZIP\\PDF\\";
            String path = DataUtils.pdfPath;
            String pdfName = pdfTutorNoInspect.getName();
            switch (applyTypeId){
                case 3: pdfName = pdfName + "博导免审表"; break;
                case 6: pdfName = pdfName + "学硕免审表"; break;
                case 9: pdfName = pdfName + "专硕免审表"; break;
            }
            pdfName = pdfName +".pdf";   //pdf名称
            path = path  + pdfName;
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
            return httpPath;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
