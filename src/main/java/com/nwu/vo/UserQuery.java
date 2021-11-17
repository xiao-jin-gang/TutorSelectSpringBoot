package com.nwu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: TutorSelectSpringBoot
 * @description: 系统用户查询参数
 * @author: dynamic
 * @create: 2021-08-10 10:55
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery {
    private int pageNum;    // 页码
    private int pageSize;   // 大小
    private String tutorId; // 工号
    private String name;    // 姓名
    private String roleId; // 角色
}
