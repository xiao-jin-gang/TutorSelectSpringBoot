package com.nwu.entities.tutor;

/**
 * @author Rex Joush
 * @time 2021.08.30 11:08
 */

import com.nwu.entities.tutor.childSubject.CourseTeaching;
import com.nwu.entities.tutor.childSubject.DeleteItem;
import com.nwu.entities.tutor.childSubject.GuidingStudent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 添加表格的第四页教学信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FourthPage {

    private List<CourseTeaching> courseTeachings;       // 研究生课程列表

    /* 此处根据不同的申请表使用的不同 */
    private List<GuidingStudent> guidingStudents;       // 指导学生列表，所有内容，用于前端进行筛选

    private List<GuidingStudent> doctorStudents;        // 指导博士生情况：博导增岗
    private List<GuidingStudent> assistDoctorStudents;  // 协助指导博士生情况：首次博导
    private List<GuidingStudent> masterStudents;        // 指导硕士生情况：首次博导，博导增岗，学硕增岗，专硕增岗
    private List<GuidingStudent> assistMasterStudents;  // 协助指导硕士生情况：首次学硕，首次专硕
    private List<GuidingStudent> undergraduateStudents; // 指导本科生情况：首次学硕，首次专硕

    private List<DeleteItem> deleteItems;               // 删除信息

}
