package com.nwu.entities.PdfEntity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class PdfNoTutorInspect {
    @TableId(value = "apply_id")
    private String applyId;
    //教师工号
    private String tutorId;
    // 姓名
    private String name;
    //所在单位名称
    private String organizationName;
    // 性别
    private String gender;
    // 出生年月
    private String birthday;
    //图片
    private String image;
    // 证件号码
    private String identity;
    // 联系电话
    private String phone;
    // 电子邮箱
    private String email;
    // 最后学位
    private String finalDegree;
    //授予单位及时间
    private String awardingUnitTime;
    // 职称
    private String title;
    // 评定时间
    private String evaluateTime;
    //研究方向
    private String researchDirections;
    //申请学科负责单位
    private String appliedSubjectUnit;
    //申请学科代码
    private String appliedSubjectCode;
    //申请学科名称
    private String appliedSubjectName;
    //免审条件
    private String exemptionConditions;

    /* 专硕免审 */
    // 申请类别负责单位
    private String professionalApplicationSubjectUnit;
    // 类别代码
    private String professionalApplicationSubjectCode;
    // 类别名称
    private String professionalApplicationSubjectName;
    // 领域代码
    private String professionalFieldCode;
    // 领域名称
    private String professionalFieldName;

    /* 博导免审 */
    //硕导上岗时间
    private String masterDirectorTime;
    //硕士招生学科
    private String masterDirectorSubject;

    //科研教学奖励
    private String teachingAwards;
    //科研项目
    private String researchProjects;
}
