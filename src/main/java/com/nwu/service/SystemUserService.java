package com.nwu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nwu.entities.SystemUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.vo.UserQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dynamic
 * @since 2021-08-10
 */
public interface SystemUserService extends IService<SystemUser> {

    int addSystemUser(String username);

    /**
     * 获取所有用户列表
     * @param query 查询信息
     * @return 列表
     */
    Page<SystemUser> getAll(UserQuery query);

    /**
     * 根据输入的工号返回模糊查询的工号列表
     * @param tutorId 工号
     * @return 以当前字段开头的工号信息
     */
    List<String> getQueryTutorId(String tutorId);

    /**
     * 根据当前用户的工号，查询基础信息
     * @param tutorId 工号
     */
    SystemUser getSystemUserByTutorId(String tutorId);
}
