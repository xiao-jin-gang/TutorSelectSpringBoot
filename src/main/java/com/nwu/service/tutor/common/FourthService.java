package com.nwu.service.tutor.common;

import com.nwu.entities.tutor.FourthPage;

/**
 * @author Rex Joush
 * @time 2021.08.30 21:05
 */

public interface FourthService {

    /**
     * 获取第四页的初始信息
     * @param id 第一页的申请表 id
     * @param tutorId 教师工号
     * @return 第四页信息
     */
    FourthPage getFourthPage(int id, String tutorId);

    /**
     * 保存第四页的信息
     * @param applyId 第一页的申请表 id
     * @param tutorId 教师工号
     * @param fourthPage 第四页信息
     */
    void updateOrSaveFourthPage(int applyId, String tutorId, FourthPage fourthPage);
}
