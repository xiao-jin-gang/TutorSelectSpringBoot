package com.nwu.service.tutor.common;

/**
 * @author Rex Joush
 * @time 2021.08.29 21:56
 */

import com.nwu.entities.tutor.SecondPage;

/**
 * 第二页的服务类
 */
public interface SecondService {

    /**
     * 更新或保存第二页的信息
     * @param applyId    第一页的申请表 id
     * @param tutorId    教师工号
     * @param secondPage 第二页的信息
     */
    void updateOrSaveSecond(int applyId, String tutorId, SecondPage secondPage);

    /**
     * 获取第二页的信息
     * @param applyId 申请表 id
     * @return 第二页信息
     */
    SecondPage getSecondPage(int applyId);


}
