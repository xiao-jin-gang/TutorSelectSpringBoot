package com.nwu.service;

/**
 * @author Rex Joush
 * @time 2021.09.08 16:08
 */

/**
 * 权限控制服务类
 */
public interface AuthorizationService {

    /**
     * 通过教师 id 获取权限列表
     * @param tutorId 教师 id
     * @return 权限列表
     */
    String getAuthorization(String tutorId);

}
