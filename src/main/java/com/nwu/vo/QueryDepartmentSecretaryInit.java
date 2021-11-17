package com.nwu.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QueryDepartmentSecretaryInit implements Serializable {
    private static final long serialVersionUID = 1L;

    // 导师非免审申请表 免审
    private String applyId;  //申请表的id
    private int applyTypeId; // 申请的类别 id
    private String tutorId;  //教师工号
    private String name; //姓名
    private String birthday; //出生年月
    private String finalDegree; //最后学位
    private String title; // 职称

    private String applyDepartment; // 申请院系
    private String applySubject; // 申请学科

    /* 博导，学硕的公共部分 */
    private String doctoralMasterApplicationSubjectUnit; //申请学科负责单位
    private String doctoralMasterSubjectCode;//一级学科代码
    private String doctoralMasterSubjectName;//一级学科名称

    /* 专硕的公共部分 */
    private String professionalApplicationSubjectUnit; //申请类别负责单位
    private String professionalApplicationSubjectCode; //类别代码
    private String professionalApplicationSubjectName; //类别名称
    private String professionalFieldCode; //领域代码
    private String professionalFieldName; // 领域名称


    /* 申请表部分 */
    private int status; // 此申请的状态
    private String commit; //秘书初审的备注
    private String commitYxSfh; //院系秘书提交分会的备注
    private String commitYxXy; //院系秘书提交分会意见页面的备注
    private String commitSocial;//社科、科研处提交研究生院管理员的备注

    /* 申请类别表 */
    private String applyName; // 申请类别名称

    /* 状态码表 */
    private String inspectDescribe;     //申请状态码简称

    // 负责院系表
    private String organizationName;  //院系名称

    private String commitYjsyCs;  // 研究生院管理员初审的备注
    private String commitYjsySfh; //研究生院管理员提交分会页面的备注
    private String commitYjsyLr; //研究生院管理员录入分会意见页面的备注

    /* 学硕博士免审表的信息 */
    private String appliedSubjectUnit;     // 申请学科_申请学科负责单位
    private String appliedSubjectCode;     // 申请学科_一级学科代码
    private String appliedSubjectName;     // 申请学科_一级学科名称

    /* 汇总表信息*/
    private int firstAuthorPaper; //核心
    private int authorityAmount ; // 权威篇数
    private int ssciAmount; // SSCI 篇数
    private int projectNationalLevel; // 国家级项目
    private int projectProvinceLevel; // 省部级项目
    private int accumulatedFunds; // 累计科研经费，万元
    private int horizontalProject; // 横向项目到款经费，万元

    private boolean isNoInspect;    // 是否为免审申请
    private int organizationId; // 导师所在院系 id

    private String summary; // 汇总信息
    private String paper;   //论文汇总信息
    private String project; //项目汇总信息
    private String work;  //教材或学术著作汇总信息
    private String awards; //教学奖励汇总信息
    private String invention;//专利汇总信息

    private String gender; //性别
    private String phone;//联系方式
}
