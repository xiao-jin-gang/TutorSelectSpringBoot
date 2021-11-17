package com.nwu.service.tutor.common;

/**
 * @author Rex Joush
 * @time 2021.08.29 20:40
 */

import com.nwu.entities.tutor.ThirdPage;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;

/**
 * 第三页的服务类
 */
public interface ThirdService {

    /**
     * 存储第三页的内容
     * @param applyId 申请的 apply 表的 id
     * @param tutorId 申请的教师工号
     * @param thirdPage 第三页信息
     */
    ThirdPage updateOrSaveThirdPage(int applyId, String tutorId, ThirdPage thirdPage, int learningType,HttpServletRequest request);

    /**
     * 获取第三页的初始信息
     * @param applyId 第一页提交的申请表 id
     * @param tutorId 教师工号
     * @return 第三页信息
     */
    ThirdPage getThirdPage(int applyId, String tutorId);

}
