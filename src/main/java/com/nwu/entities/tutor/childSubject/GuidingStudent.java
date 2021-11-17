package com.nwu.entities.tutor.childSubject;

/**
 * @author Rex Joush
 * @time 2021.08.30 21:24
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 指导学生表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("guiding_student")
public class GuidingStudent {

    @TableId(value = "student_id", type = IdType.AUTO)
    private int studentId           ; // 学生 id
    private String tutorId          ; // 学生所属教师 id
    private int applyId             ; // 所属的申请

    /* 学生信息 */
    private String studentName      ; // 学生姓名
    private String studentType      ; // 学生类型，用于区分是本科生，硕士生和博士生
    private String studentEntryTime ; // 学生入学时间
    private String degreePaperTitle ; // 学位论文题目
    private String isGainDegree     ; // 是否获得学位
    private String directType       ; // 指导类型
    private String tutorName        ; // 教师姓名
    private String tutorNumber      ; // 教师编号
    private String graduateTime     ; // 毕业时间

    /* 保留字段 */
    private String col1             ; // 保留字段1
    private String col2             ; // 保留字段2
    private String col3             ; // 保留字段3
    private String col4             ; // 保留字段4

}
