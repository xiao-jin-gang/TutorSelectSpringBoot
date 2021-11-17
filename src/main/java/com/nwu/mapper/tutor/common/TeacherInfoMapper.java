package com.nwu.mapper.tutor.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nwu.entities.AcademicPaper;
import com.nwu.entities.SystemUser;
import com.nwu.entities.tutor.FirstPage;
import com.nwu.entities.tutor.TeacherInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Rex Joush
 * @time 2021.08.23 15:27
 */

/**
 * 登录时添加教师信息
 */
@Mapper
public interface TeacherInfoMapper extends BaseMapper<TeacherInfo> {
    /**
     * 获取教师基本信息
     * @param tutorId
     * @return
     */
    FirstPage getTeacherInfo(@Param("tutorId") String tutorId);

    SystemUser getSystemUserInfo(@Param("tutorId") String tutorId);

}
