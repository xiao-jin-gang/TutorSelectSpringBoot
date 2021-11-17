package com.nwu.entities;

/**
 * @author Rex Joush
 * @time 2021.08.29 17:31
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 申请表中的总结信息
 */
@Data
@TableName("summary")
@NoArgsConstructor
@AllArgsConstructor
public class Summary {

    @TableId(value = "summary_id", type = IdType.AUTO)
    private int summaryId              ; // 论文 id
    private String tutorId             ; // 论文所属教师 id
    private int applyId                ; // 申请类别的id，即此论文申请的类别

    /* 汇总字段 */
    private int firstAuthorPaper       ; // 以第一作者或通讯在 核心及以上期刊发表与本学科发表的论文篇数
    private int authorityAmount        ; // 权威篇数
    private int eiAmount               ; // EI 篇数
    private int ssciAmount             ; // SSCI 篇数
    private int ahciAmount             ; // A&HCI 篇数
    private int cssciAmount            ; // CSSCI 篇数
    private int cscdAmount             ; // CSCD 篇数
    private int cpciAmount             ; // CPCI 篇数
    private int directProject          ; // 主持科研项目
    private int projectNationalLevel   ; // 国家级项目
    private int projectProvinceLevel   ; // 省部级项目
    private double accumulatedFunds    ; // 累计科研经费，万元
    private double horizontalProject   ; // 横向项目到款经费，万元
    private int publishWorks           ; // 出版专业领域专著数
    private double publishWorksWords   ; // 本人完成字数
    private int scientificAwards       ; // 科研教学获奖项数
    private int awardsNationalLevel    ; // 国家级奖项
    private int awardsProvinceLevel    ; // 省部级奖项
    private int inventionPatentAmount  ; // 发明专利
    private int newUtilityPatent       ; // 新型实用专利

    /**
     * 获取所有汇总信息的字符串
     */
    public String getSummaryString() {
        return "1.以第一作者或通讯作者在核心及以上期刊发表与本学科相关的学术论文共 " + firstAuthorPaper + " 篇，其中" +
                    "权威 " + authorityAmount + " 篇，" +
                    "EI " + eiAmount + " 篇，" +
                    "CSCD " + cscdAmount + " 篇，" +
                    "A&HCI " + ahciAmount + " 篇，" +
                    "CSSCI " + cssciAmount + " 篇，" +
                    "CPCI " + cpciAmount + " 篇。\n" +
                "2.主持在研科研项目共 " + directProject + " 项，" +
                    "其中国家级 " + projectNationalLevel + " 项，" +
                    "省部级 " + projectProvinceLevel + " 项；" +
                    "在研项目中累计到款科研经费 " + accumulatedFunds + " 万元，" +
                    "其中横向项目到款经费 " + horizontalProject + " 万元。\n" +
                "3.出版本专业领域内研究生" +
                    "教材或学术著作（译著） " + publishWorks + " 部，" +
                    "每部本人完成 " + publishWorksWords + " 万字。\n" +
                "4.科研教学获奖" +
                    "共 " + scientificAwards + " 项，" +
                    "其中国家级 " + awardsNationalLevel + " 项，" +
                    "省部级 " + awardsProvinceLevel + " 项。\n" +
                "5.以第一发明人授权职务" +
                    "发明专利 " + inventionPatentAmount + " 项，" +
                    "实用新型专利 " + newUtilityPatent + " 项。";

    }

    /**
     * 获取 学术论文 部分的汇总字符串
     */
    public String getSummaryAcademicPaperString() {
        return "以第一作者或通讯作者在核心及以上期刊发表与本学科相关的学术论文共 " + firstAuthorPaper + " 篇，其中" +
                "权威 " + authorityAmount + " 篇，" +
                "EI " + eiAmount + " 篇，" +
                "CSCD " + cscdAmount + " 篇，" +
                "A&HCI " + ahciAmount + " 篇，" +
                "CSSCI " + cssciAmount + " 篇，" +
                "CPCI " + cpciAmount + " 篇。\n";
    }

    /**
     * 获取 科研项目 部分的汇总字符串
     */
    public String getSummaryResearchProjectsString() {
        return "主持在研科研项目共 " + directProject + " 项，" +
                "其中国家级 " + projectNationalLevel + " 项，" +
                "省部级 " + projectProvinceLevel + " 项；" +
                "在研项目中累计到款科研经费 " + accumulatedFunds + " 万元，" +
                "其中横向项目到款经费 " + horizontalProject + " 万元。";
    }

    /**
     * 获取 教材或学术著作 部分的汇总字符串
     */
    public String getSummaryAcademicWorksString() {
        return "出版本专业领域内研究生" +
                "教材或学术著作（译著） " + publishWorks + " 部，" +
                "每部本人完成 " + publishWorksWords + " 万字。";
    }

    /**
     * 获取 科研教学奖励 部分的汇总字符串
     */
    public String getSummaryTeachingAwardsString() {
        return "科研教学获奖" +
                "共 " + scientificAwards + " 项，" +
                "其中国家级 " + awardsNationalLevel + " 项，" +
                "省部级 " + awardsProvinceLevel + " 项。";
    }

    /**
     * 获取 发明专利 部分的汇总字符串
     */
    public String getSummaryInventionPatentsString() {
        return "以第一发明人授权职务" +
                "发明专利 " + inventionPatentAmount + " 项，" +
                "实用新型专利 " + newUtilityPatent + " 项。";
    }
}
