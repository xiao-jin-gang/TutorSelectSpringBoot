package com.nwu.service.tutor.common;

import com.nwu.entities.Apply;
import com.nwu.entities.tutor.FirstPage;

import javax.servlet.http.HttpServletRequest;

public interface FirstService {
    /**
     * 存储 apply 表
     * @param apply 申请信息
     * @return 返回主键
     */
    int saveApplyInfo(Apply apply);

    /**
     * 更新第一页的信息
     * @param applyId   申请表 id
     * @param phone     电话
     * @param email     邮箱
     * @param evaluateTime      职称评定时间
     * @param awardingUnitTime  学位授予单位及时间
     * @return 修改结果
     */
    int updateFirstPage(String applyId, String phone, String email, String evaluateTime, String awardingUnitTime);


    /**
     * 插入第一页信息
     * @param firstPage 第一页的信息
     * @return 修改插入结果
     */
    int saveFirstPage(FirstPage firstPage, HttpServletRequest request);


}




