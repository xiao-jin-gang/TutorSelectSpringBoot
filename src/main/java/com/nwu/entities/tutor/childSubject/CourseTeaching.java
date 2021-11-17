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
 * 所教课程表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("course_teaching")
public class CourseTeaching {

    @TableId(value = "course_id", type = IdType.AUTO)
    private int courseId           ; // 课程 id
    private String tutorId         ; // 课程所属教师 id
    private int applyId            ; // 课程申请类别的id，即此教学课程申请的类别

    /* 课程教学信息 */
    private String courseName      ; // 课程名称
    private String courseTime      ; // 课程时间
    private String courseDuration  ; // 授课总课时
    private String courseObject    ; // 授课对象

    /* 保留字段 */
    private String col1             ; // 保留字段1
    private String col2             ; // 保留字段2
    private String col3             ; // 保留字段3
    private String col4             ; // 保留字段4
}
