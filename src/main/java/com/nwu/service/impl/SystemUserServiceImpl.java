package com.nwu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nwu.entities.SystemUser;
import com.nwu.mapper.SystemUserMapper;
import com.nwu.service.SystemUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.service.tutor.common.TeacherInfoService;
import com.nwu.util.TimeUtils;
import com.nwu.vo.UserQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author dynamic
 * @since 2021-08-10
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {

    @Resource
    private SystemUserMapper systemUserMapper;

    @Resource
    private TeacherInfoService teacherInfoService;

    @Override
    public int addSystemUser(String tutorId) {

        try {
            SystemUser systemUser = teacherInfoService.getSystemUserInfo(tutorId);
            if (systemUser == null) {
                return -1;
            }
            systemUser.setTutorId(tutorId);
            systemUser.setRoleName("导师");
            systemUser.setRoleId(1);
            systemUser.setStatus(1);
            systemUser.setCreateTime(TimeUtils.sdf.format(new Date()));
            systemUserMapper.insert(systemUser);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("导师信息不存在");
        }
    }

    @Override
    public Page<SystemUser> getAll(UserQuery query) {
        Page<SystemUser> page = new Page<>();
        QueryWrapper<SystemUser> queryWrapper = new QueryWrapper<>();

        if (!"".equals(query.getName())) {
            queryWrapper.like("name", "%" + query.getName() + "%");
            query.setName("%" + query.getName() + "%");
        }
        if (!"".equals(query.getRoleId())) {
            queryWrapper.eq("role_id", query.getRoleId());
        }
        if (!"".equals(query.getTutorId())) {
            queryWrapper.like("tutor_id", query.getTutorId() + "%");
            query.setTutorId(query.getTutorId() + "%");
        }
        queryWrapper.eq("status", 1);
        queryWrapper.in("role_id", 2, 4, 6, 7);

        List<SystemUser> users = systemUserMapper.getAll(query, (query.getPageNum() - 1) * 10);

        page.setRecords(users);
        page.setTotal(systemUserMapper.selectCount(queryWrapper));

        return page;
    }

    @Override
    public List<String> getQueryTutorId(String tutorId) {
        return systemUserMapper.getQueryTutorId(tutorId + "%");
    }

    @Override
    public SystemUser getSystemUserByTutorId(String tutorId) {
        return systemUserMapper.getSystemUserByTutorId(tutorId);
    }
}
