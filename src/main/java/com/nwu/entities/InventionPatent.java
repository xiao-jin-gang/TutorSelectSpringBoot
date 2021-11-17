package com.nwu.entities; 

import com.baomidou.mybatisplus.annotation.IdType; 
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Rex Joush
 * @time 2021.08.29 17:32
 */

/**
 * 发明专利表
 */
@Data
@TableName("invention_patent")
public class InventionPatent {

    @TableId(value = "patent_id", type = IdType.AUTO)
    private int patentId                ; // 专利 id
    private String tutorId              ; // 教师工号
    private int applyId                 ; // 所属的某项申请

    private String patentName           ; // 专利名称
    private String patentNumber         ; // 专利编号
    private String patentRank           ; // 排名
    private String patentGrantTime      ; // 专利授权日期
    private String patentGrantNumber    ; // 专利授权号
    private String patentType           ; // 专利类型
    private String patentAuthorName     ; // 作者姓名
    private String patentAuthorNumber   ; // 作者编号
    private String patentSource         ; // 专利的来源，系统获取 或 导师上传
    private String patentProveMaterials ; // 证明材料，图片，pdf等

    /* 保留字段 */
    private String col1                 ; // 保留字段1
    private String col2                 ; // 保留字段2
    private String col3                 ; // 保留字段3
    private String col4                 ; // 保留字段4

}
