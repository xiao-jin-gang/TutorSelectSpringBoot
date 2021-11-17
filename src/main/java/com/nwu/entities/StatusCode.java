package com.nwu.entities;

/**
 * @author Rex Joush
 * @time 2021.10.23 13:29
 */

/**
 * 状态码的枚举类
 */
public enum StatusCode {

    TUTOR_UN_COMMIT(0),                     // 导师未提交
    TUTOR_COMPLETE_FILLING(6),              // 导师填写完成，但未提交至院系秘书
    DEPARTMENT_UN_CHECK(10),                // 院系秘书初审待审核
    DEPARTMENT_FIRST_CHECK_IN(11),          // 院系秘书初审符合条件，院系主管页面待审核的显示
    DEPARTMENT_FIRST_CHECK_OUT(12),         // 院系秘书初审不符合条件。院系主管页面待审核的显示
    DEPARTMENT_FIRST_CHECK_UNKNOWN(13),     // 院系秘书初审待定，院系主管页面待审核的显示
    DEPARTMENT_FIRST_CHECK_MODIFY(14);      // 院系秘书初审需修改，直接驳回给导师修改，查看教师返回修改页面显示，导师页面显示


    private final int codeId;

    StatusCode(int codeId){
        this.codeId = codeId;
    }

    public int codeId(){
        return codeId;
    }

}
