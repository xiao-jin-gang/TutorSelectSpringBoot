package com.nwu.service.scientificResearchManager;

import com.alibaba.fastjson.JSONObject;

public interface ScienceService {

    /**
     * 获取教师的申请材料
     * @param tutorId 教师工号
     * @param applyId 申请 id
     */
    JSONObject getInit(String tutorId, int applyId);

    /**
     * 审核方法
     * @param id 材料的 id
     *
     * @param type
     *         1 论文
     *         2 项目
     *         3 教材或著作
     *         4 奖励
     *         5 专利
     * @param passOrReject 审核通过或拒绝
     *         1 通过
     *         2 拒绝
     * @param col2 拒绝的备注信息
     */
    void check(int id, int type, int passOrReject, String col2);

    /**
     * 提交材料审核
     * @param applyId 申请 id
     * @param type 类型
     *             1 审核通过
     *             2 审核不通过
     * @param commitSocial 审核不通过的拼接备注
     * @param science 社科处或科研处区分
     *                1 社科处
     *                2 科研处
     */
    void commitMaterial(int applyId, int type, String commitSocial, int science);
}
