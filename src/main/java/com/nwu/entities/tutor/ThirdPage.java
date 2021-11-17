package com.nwu.entities.tutor;

/**
 * @author Rex Joush
 * @time 2021.08.29 17:26
 */

import com.nwu.entities.*;
import com.nwu.entities.tutor.childSubject.DeleteItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 添加表格的第三页学术信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThirdPage {

    // 学术论文
    private List<AcademicPaper> academicPapers;

    // 科研项目
    private List<ResearchProject> researchProjects;

    // 教材或学术著作
    private List<AcademicWorks> academicWorks;

    // 教学获奖
    private List<TeachingAwards> teachingAwards;

    // 发明专利
    private List<InventionPatent> inventionPatents;

    // 总结信息
    private Summary summary;

    // 删除信息列表
    private List<DeleteItem> deleteItems;
}
