package com.nwu.entities.tutor;

/**
 * @author Rex Joush
 * @time 2021.08.24 16:05
 */

import com.nwu.entities.tutor.childSubject.ExpertTitle;
import com.nwu.entities.tutor.childSubject.GroupsOrPartTimeJob;
import lombok.Data;

import java.util.List;

/**
 * 添加表格的第二页研究信息
 */
@Data
public class SecondPage {

    private int applyId; // 申请 id
    private String applySubject;    // 申请学科

    private String major; // 主要研究方向的内容及其意义
    private List<GroupsOrPartTimeJob> groupsOrPartTimeJobs;    // 何时参加何种学术团体、任何种职务，有何社会兼职列表
    private String groupsOrPartTimeJobsJson;    // 上一个字段的 json 串 存数据库
    private List<ExpertTitle> expertTitles;    // 获何专家称号及时间列表
    private String expertTitlesJson;    // 获何专家称号及时间列表 json 串


    /* 外单位调入填写 */
    private String sourceUnitName; // 原单位名称
    private String transferInTime; // 原单位调入时间

    /* 首次博导，博导增岗，首次学硕，学硕增岗 */
    // private String title; // 职称

    /* 首次专硕，专硕增岗 */
    private String professionalTitle; // 现任专业技术职位

    // private String evaluateTime; // 评定时间


    /* 首次博导 */
    private String firstDoctoralFirstTimeTutor;     // 任硕导时间
    private String firstDoctoralFirstDiscipline;     // 任硕导一级学科

    /* 博导增岗 */
    private String doctoralTutorOnDuty; // 博导在岗学科
    private String doctoralStartTime;  // 上岗时间

    /* 学硕增岗 */
    private String masterDegreeIncreaseTutorOnDuty;     // 硕导在岗学科
    private String masterDegreeIncreaseTutorStartTime;     // 上岗时间


    /* 博导，学硕的公共部分 */
    private String doctoralMasterApplicationSubjectUnit;    // 申请学科负责单位
    private String doctoralMasterSubjectCodeName;   // 一级学科代码 + " " + 名称
    private String doctoralMasterSubjectCode;   // 一级学科代码
    private String doctoralMasterSubjectName;   // 一级学科名称

    /* 专硕增岗 */
    private String professionalMasterOnDuty;     // 硕导在岗类别
    private String professionalMasterStartTime;     // 上岗时间

    /* 专硕的公共部分 */
    private String professionalApplicationSubjectUnit;     // 申请类别负责单位
    private String professionalApplicationSubjectCodeName; // 申请类别代码 + " " + 类别名称
    private String professionalApplicationSubjectCode;     // 类别代码
    private String professionalApplicationSubjectName;     // 类别名称
    private String professionalFieldCodeName; // 领域代码 + " " + 领域名称
    private String professionalFieldCode;     // 领域代码
    private String professionalFieldName;     // 领域名称

}
