package com.nwu.service.tutor.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.entities.SystemUser;
import com.nwu.entities.tutor.FirstPage;
import com.nwu.entities.tutor.TeacherInfo;

/**
 * @author Rex Joush
 * @time 2021.08.23 15:29
 */

public interface TeacherInfoService extends IService<TeacherInfo> {

    /**
     * 根据工号获取教师信息
     * @param tutorId 教师工号
     * @return 第一页基础信息
     */
    FirstPage getTeacherInfo(String tutorId);

    /**
     * 获取系统用户的院系信息，id 和 名称
     * @param tutorId 教师工号
     * @return 当前老师所在的院系信息
     */
    SystemUser getSystemUserInfo(String tutorId);
}
