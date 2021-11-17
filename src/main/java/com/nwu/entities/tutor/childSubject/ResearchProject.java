package com.nwu.entities.tutor.childSubject;

import lombok.Data;

@Data
public class ResearchProject {

    /* 导师免审 科研项目情况*/

    //项目名称
    private String projectName;
    //批准号
    private String approvalNumber;
    //开始日期
    private String projectStartTime;
    //结束日期
    private String projectEndTime;
    //总经费
    private String projectTotalPrice;
    //项目级别
    private String projectLevel;
    //项目分类
    private String projectCategory;
    //负责人姓名
    private String projectChargeName;

}
