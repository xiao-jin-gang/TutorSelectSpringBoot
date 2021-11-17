package com.nwu.entities.PdfEntity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PdfTutorInspect {

    @TableId(value = "apply_id")
    private String applyId;
    //教师工号
    private String tutorId;
    // 姓名
    private String name;
    // 所在单位
    private Integer organizationId;
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
    //申请类别
    private Integer subject;

    private String researchDirections; //主要研究方向的内容及其意义

    private String academicGroupsJobs; //何时参加何种学术 团体、任何种职务有何社会兼职

    private String titlesOfExpert; //获何专家称号及时间
    //职称
    private String title; // 职称

    private String professionalTitle; // 现任专业技术职位，首次专硕，专硕增岗
    // 评定时间
    private String evaluateTime; //评定时间

    /* 外单位调入填写 */

    private String sourceUnitName; //原单位名称

    private String transferInTime; //调入时间

    /* 博导增岗 */

    private String doctoralTutorOnDuty; //博导在岗学科

    private String doctoralStartTime; //上岗时间

    /* 首次博导 */
    private String firstDoctoralFirstTimeTutor; // 任硕导时间

    private String firstDoctoralFirstDiscipline; // 任硕导一级学科

    /* 首次学硕 */

    /* 学硕增岗 */
    private String masterDegreeIncreaseTutorOnDuty;//硕导在岗学科

    private String masterDegreeIncreaseTutorStartTime;//上岗时间

    /* 博导，学硕的公共部分 */

    private String doctoralMasterApplicationSubjectUnit; //申请学科负责单位

    private String doctoralMasterSubjectCode;//一级学科代码

    private String doctoralMasterSubjectName;//一级学科名称

    /* 首次专硕 */

    /* 专硕增岗 */

    private String professionalMasterOnDuty;//硕导在岗类别

    private String professionalMasterStartTime;//上岗时间

    /* 专硕的公共部分 */

    private String professionalApplicationSubjectUnit; //申请类别负责单位

    private String professionalApplicationSubjectCode; //类别代码

    private String professionalApplicationSubjectName; //类别名称

    private String professionalFieldCode; //领域代码

    private String professionalFieldName; // 领域名称

    private String col1;

    private String col2;

    private String col3;

    private String col4;

}
