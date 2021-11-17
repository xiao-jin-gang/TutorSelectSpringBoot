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
 * @since 2021-08-27
 */
@Data
@TableName("research_project")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ResearchProject对象")
public class ResearchProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "project_id", type = IdType.AUTO)
    private int projectId               ; // 项目 id
    private String tutorId              ; // 所属教师 id
    private Integer applyId             ; // 所属申请 id

    private String projectName          ; // 项目名称
    private String projectNumber        ; // 项目编号
    private String approvalNumber       ; // 批准号
    private String projectRank          ; // 排名
    private String projectStartTime     ; // 开始日期
    private String projectEndTime       ; // 结束日期
    private String projectSourceUnit    ; // 项目来源单位
    private double projectTotalPrice    ; // 总经费
    private String projectLevel         ; // 项目级别
    private String projectCategory      ; // 项目分类
    private String projectChargeName    ; // 负责人姓名
    private String projectChargeNumber  ; // 负责人编号
    private String projectSubject       ; // 项目分科，文科或者理科或者交叉学科
    private String projectSource        ; // 项目的来源，系统获取 或 导师上传
    private String projectProveMaterials; // 证明材料，图片，pdf等

    private String col1                 ; // 保留字段1
    private String col2                 ; // 保留字段2
    private String col3                 ; // 保留字段3
    private String col4                 ; // 保留字段4

}
