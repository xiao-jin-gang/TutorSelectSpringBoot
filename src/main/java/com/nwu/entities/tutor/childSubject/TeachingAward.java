package com.nwu.entities.tutor.childSubject;

import lombok.Data;

@Data
public class TeachingAward {

    /* 导师免审 科研教学奖励*/
    //奖励名称
    private String awardsName;
    //排名
    private String awardsRank;
    //颁奖单位
    private String awardsUnit;
    //获奖日期
    private String awardsTime;
    //获奖人姓名
    private String awardsAuthorName;
    //获奖级别
    private String awardsLevel;
}
