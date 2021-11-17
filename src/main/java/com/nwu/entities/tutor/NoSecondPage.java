package com.nwu.entities.tutor;

import com.nwu.entities.tutor.childSubject.ResearchProject;
import com.nwu.entities.tutor.childSubject.TeachingAward;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoSecondPage {

    // 免审
    private int applyId;        // 申请 id
    private String researchDirections;  // 主要研究方向

    private String appliedSubjectUnit;  // 学硕申请学科负责单位
    private String doctoralMasterSubjectCodeName;   // 一级学科代码及名称 辅助字段
    private String appliedSubjectCode;  // 学硕申请学科代码
    private String appliedSubjectName;  // 学硕申请学科名称


    private String professionalApplicationSubjectUnit; // 申请类别负责单位
    private String professionalApplicationSubjectCode; // 类别代码
    private String professionalApplicationSubjectName;  // 类别名称
    private String professionalApplicationSubjectCodeName; // 申请类别代码 + " " + 类别名称
    private String professionalFieldCode;   // 领域代码
    private String professionalFieldName;   // 领域名称
    private String professionalFieldCodeName; // 领域代码 + " " + 领域名称

    private String exemptionConditions; // 免审条件
    private String exemptionConditionsMaterials;    // 免审条件路径
    private String applySubject;   // 申请类别 文史 理工 交叉

    /* 科研教学情况 */
    private String researchProjectsJson;    // 科研项目 存入数据库
    private List<ResearchProject> researchProjects; // 科研项目 辅助字段
    private String teachingAwardsJson;  // 科研教学情况 存入数据库
    private List<TeachingAward> teachingAwards; // 科研教学情况 辅助字段

    /* 博导免审 */
    private String masterDirectorTime;  // 硕导上岗时间
    private String masterDirectorSubject;   // 硕士招生学科


}
