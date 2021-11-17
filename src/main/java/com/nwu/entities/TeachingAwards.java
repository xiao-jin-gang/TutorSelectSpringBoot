package com.nwu.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
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
@TableName("teaching_awards")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TeachingAwards对象")
public class TeachingAwards implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "awards_id", type = IdType.AUTO)
    private Integer awardsId            ; // 著作 id
    private String tutorId              ; // 教学获奖所属教师
    private Integer applyId             ; // 论文所属的申请

    private String awardsName           ; // 奖励名称
    private String awardsRank           ; // 排名
    private String awardsUnit           ; // 颁奖单位
    private String awardsClass          ; // 获奖等级
    private String awardsLevel          ; // 获奖级别
    private String awardsTime           ; // 获奖日期
    private String awardsAuthorName     ; // 获奖人姓名
    private String awardsAuthorNumber   ; // 获奖人编号
    private String awardsSource         ; // 获奖的来源，系统获取 或 导师上传
    private String awardsProveMaterials ; // 证明材料，图片，pdf等

    private String col1                 ; // 保留字段1
    private String col2                 ; // 保留字段2
    private String col3                 ; // 保留字段3
    private String col4                 ; // 保留字段4


}
