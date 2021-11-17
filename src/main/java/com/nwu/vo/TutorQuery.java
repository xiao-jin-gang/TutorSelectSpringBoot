package com.nwu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: TutorSelectSpringBoot
 * @description: 管理员筛选查询条件
 * @author: dynamic
 * @create: 2021-08-17 15:12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TutorQuery {
    // 学号
    private String userId;
    // 姓名
    private String userName;
    // 部门id
    private int organization;
    // 院系名称
    private String organizationName;
    // 申请类别
    private int applyType;
    // 学科属性
    private String subjectType;
    // 学科名称
     private String subjectName;

    // 申请状态码
    private String applyStatus;

    // 申请状态码
    private List<String> applyStatuss;


}
