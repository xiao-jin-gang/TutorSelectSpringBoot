package com.nwu.service.tutor;

/**
 * @author Rex Joush
 * @time 2021.09.02 10:25
 */

import com.alibaba.fastjson.JSONObject;
import com.nwu.entities.Summary;
import com.nwu.entities.tutor.FourthPage;
import com.nwu.entities.tutor.SecondPage;
import com.nwu.entities.tutor.ThirdPage;
import com.nwu.entities.tutor.NoSecondPage;

import java.util.ArrayList;

/**
 * 获取某些页面的初始化信息
 */
public class PageInit {

    /**
     * 获取第二页的空对象
     * @return 第二页空对象
     */
    public static SecondPage getSecondPage() {

        SecondPage secondPage = new SecondPage();
        secondPage.setExpertTitles(new ArrayList<>());
        secondPage.setGroupsOrPartTimeJobs(new ArrayList<>());
        return secondPage;

    }

    /**
     * 获取免审第二页的空对象
     * @return 面审第二页的空对象
     */
    public static NoSecondPage getNoSecondPage() {

        NoSecondPage secondPage = new NoSecondPage();
        secondPage.setResearchProjects(new ArrayList<>());
        secondPage.setTeachingAwards(new ArrayList<>());
        return secondPage;
    }

    /**
     * 获取第三页的初始化空对象
     * @return 第三页空对象
     */
    public static ThirdPage getThirdPage() {

        ThirdPage thirdPage = new ThirdPage();

        thirdPage.setAcademicPapers(new ArrayList<>());
        thirdPage.setResearchProjects(new ArrayList<>());
        thirdPage.setAcademicWorks(new ArrayList<>());
        thirdPage.setTeachingAwards(new ArrayList<>());
        thirdPage.setInventionPatents(new ArrayList<>());
        thirdPage.setSummary(getSummary());
        thirdPage.setDeleteItems(new ArrayList<>());

        return thirdPage;
    }

    /**
     * 获取第四页的初始化空对象
     * @return 第四页空对象
     */
    public static FourthPage getFourthPage() {
        FourthPage fourthPage = new FourthPage();

        fourthPage.setCourseTeachings(new ArrayList<>());
        fourthPage.setGuidingStudents(new ArrayList<>());
        fourthPage.setDoctorStudents(new ArrayList<>());
        fourthPage.setAssistDoctorStudents(new ArrayList<>());
        fourthPage.setMasterStudents(new ArrayList<>());
        fourthPage.setAssistMasterStudents(new ArrayList<>());
        fourthPage.setUndergraduateStudents(new ArrayList<>());
        fourthPage.setDeleteItems(new ArrayList<>());

        return fourthPage;
    }

    /**
     * 获取第三页的总结信息
     * @return 总结信息空对象
     */
    public static Summary getSummary() {

        Summary summary = new Summary();

        summary.setFirstAuthorPaper(0);
        summary.setAuthorityAmount(0);
        summary.setEiAmount(0);
        summary.setSsciAmount(0);
        summary.setAhciAmount(0);
        summary.setCssciAmount(0);
        summary.setCscdAmount(0);
        summary.setCpciAmount(0);
        summary.setDirectProject(0);
        summary.setProjectNationalLevel(0);
        summary.setProjectProvinceLevel(0);
        summary.setAccumulatedFunds(0);
        summary.setHorizontalProject(0);
        summary.setPublishWorks(0);
        summary.setPublishWorksWords(0);
        summary.setScientificAwards(0);
        summary.setAwardsNationalLevel(0);
        summary.setAwardsProvinceLevel(0);
        summary.setInventionPatentAmount(0);
        summary.setNewUtilityPatent(0);

        return summary;

    }

    /**
     * 获取异常信息，并封装成对象返回
     * @param e 异常信息，a!b
     *          a 表示提示信息
     *          b 表示异常信息
     * @return 封装好的对象
     */
    public static JSONObject getErrorMessage(Exception e){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 1201);
        jsonObject.put("message", e.getMessage().split("!")[0]);
        jsonObject.put("errorMessage", e.getMessage().split("!")[1]);

        return jsonObject;
    }

}
