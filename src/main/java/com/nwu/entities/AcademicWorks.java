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
@TableName("academic_works")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AcademicWorks对象")
public class AcademicWorks implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "works_id", type = IdType.AUTO)
    private int worksId                 ; // 著作 id
    private String tutorId              ; // apply表中的user_id  教师工号
    private Integer applyId                 ; // 著作所属的申请

    private String worksName            ; // 著作名称
    private String worksNumber          ; // 著作编号
    private String worksRank            ; // 排名
    private String worksPublicationTime ; // 出版日期
    private String worksPublicationUnit ; // 出版单位
    private String totalWords           ; // 完成字数
    private String authorName           ; // 作者姓名
    private String worksSource          ; // 项目的来源，系统获取 或 导师上传
    private String worksProveMaterials  ; // 证明材料，图片，pdf等
    
    private String col1                 ; // 保留字段1
    private String col2                 ; // 保留字段2
    private String col3                 ; // 保留字段3
    private String col4                 ; // 保留字段4

}
