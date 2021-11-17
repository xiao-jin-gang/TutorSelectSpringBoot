package com.nwu.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Anna
 * @since 2021-08-26
 */
@Data
@TableName("academic_paper")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AcademicPaper对象", description = "")
public class AcademicPaper implements Serializable {

    private static final long serialVersionUID = 1L; // 

    @ApiModelProperty(value = "论文的唯一标识")
    @TableId(value = "paper_id", type = IdType.AUTO)
    private Integer paperId                 ; // 论文 id
    private String tutorId                  ; // 论文所属教师 id
    private Integer applyId                     ; // 论文所属的申请

    private String paperName                ; // 论文名称
    private String paperNumber              ; // 论文编号
    private String paperRank                ; // 排名
    private String paperPublicationTime     ; // 发表日期
    private String journalName              ; // 期刊名称
    private String journalLevel             ; // 期刊等级
    private String journalCategory          ; // 收录类别
    private String sciPart                  ; // 分区
    private double impactFactors            ; // 影响因子
    private String authorName               ; // 作者姓名
    private String firstAuthorName          ; // 第一作者
    private String communicationAuthorName  ; // 通讯作者
    private String communicationAuthorNumber; // 工号
    private String paperSubject             ; // 论文分科，文科或者理科或者交叉学科
    private String paperSource              ; // 论文的来源，系统获取 或 导师上传
    private String paperProveMaterials      ; // 证明材料，图片，pdf等

    private String col1                     ; // 保留字段1
    private String col2                     ; // 保留字段2
    private String col3                     ; // 保留字段3
    private String col4                     ; // 保留字段4


}
