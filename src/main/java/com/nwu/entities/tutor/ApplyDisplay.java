package com.nwu.entities.tutor;

/**
 * @author Rex Joush
 * @time 2021.09.03 15:45
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 我的申请展示页面的实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyDisplay implements Serializable {

    private int applyId;            // 申请表的 id
    private String tutorId;         // 教师工号
    private int applyTypeId;        // 申请的类型 id
    private int status;             // 当前的状态 id
    private String applyName;       // 申请类型
    private String statusDisplay;   // 当前状态
    private String commitGraduate;  // 研究生院备注
    private String commitSocial;    // 社科处/科研处 备注
    private String commit;          // 院系备注

    private boolean isNoInspect;    // 是否为免审申请

    private String applyDepartment; // 申请学科所在学院的展示
    private String applySubject;    // 申请学科的展示

}
