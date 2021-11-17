package com.nwu.service.tutor.noInspectApply;

import com.nwu.entities.Apply;
import com.nwu.entities.tutor.FirstPage;

import javax.servlet.http.HttpServletRequest;

public interface NoFirstService {

    /**
     * 存储 apply 表
     * @param apply 申请信息
     * @return 返回主键
     */
    int saveNoApplyInfo(Apply apply);

    /**
     * 更新第一页的信息
     * @param applyId   申请表 id
     * @param phone     电话
     * @param email     邮箱
     * @param evaluateTime      职称评定时间
     * @param awardingUnitTime  学位授予单位及时间
     * @return 修改结果
     */
    int updateNoFirstPage(String applyId, String phone, String email, String evaluateTime, String awardingUnitTime);

    /**
     * 查询免审第一页信息 tutor_no_inspect
     * @param applyId
     * @return
     */
    FirstPage getNoFirstPage(int applyId) throws Exception;

    /**
     * 保存免审第一页信息 tutor_no_inspect
     * @param noFirstPage
     * @param request
     */
    int saveNoFirstPage(FirstPage noFirstPage, HttpServletRequest request) throws Exception;

}
