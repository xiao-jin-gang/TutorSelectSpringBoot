package com.nwu.service.tutor.noInspectApply;

import com.nwu.entities.tutor.NoSecondPage;

/**
 * @author zjz
 * @time 2021.08.20 14:48
 */

public interface NoSecondService {

    /**
     * 获取第二页信息 查询数据库
     * @param applyId 申请 id
     */
    NoSecondPage getSecondPage(int applyId);

    /**
     * 更新第二页信息
     * @param noSecondPage 免审第二页
     */
    void updateNoSecondPage(NoSecondPage noSecondPage, int applyId);

}
