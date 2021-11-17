package com.nwu.service;

import com.nwu.entities.PdfEntity.PdfTutorInspect;
import com.nwu.entities.TutorInspect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.entities.tutor.FirstPage;

import com.nwu.entities.tutor.ThirdPage;
import com.nwu.vo.QueryDepartmentSecretaryInit;

import com.nwu.entities.tutor.SecondPage;

import com.nwu.vo.TutorQuery;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dynamic
 * @since 2021-08-09
 */
public interface TutorInspectService extends IService<TutorInspect> {

    /**
     * 获取所有教师信息
     * @param organizationId 院系 id
     * @param applyStatuss 申请的状态类别
     * @param tutorQuery 查询信息，条件查询使用
     * @param type 当前的查询是否为条件查询
     *        0 获取所有
     *        1 条件查询
     * @return 信息列表
     */
    List<QueryDepartmentSecretaryInit> getTutorInitOrSearch(int organizationId, List<String> applyStatuss, int pageNumber, TutorQuery tutorQuery, int type);

    /**
     * 获取所有教师信息的总条数
     * @param organizationId 院系 id
     * @param applyStatuss 申请状态列表
     * @param tutorQuery 查询信息，条件查询使用
     * @param type 当前的查询是否为条件查询
     *        0 获取所有
     *        1 条件查询
     * @return 信息列表
     */
    int getTutorInitOrSearchTotal(int organizationId, List<String> applyStatuss, TutorQuery tutorQuery, int type);

    /**
     * 导出申请信息的 excel 表格
     * @param organizationId 院系 id
     * @param applyStatuss 申请状态列表
     * @param tutorQuery 查询信息，条件查询使用
     * @param type 当前的查询是否为条件查询
     *        0 获取所有
     *        1 条件查询
     * @return 信息列表
     */
    List<QueryDepartmentSecretaryInit> exportTutorInitOrSearch(int organizationId, List<String> applyStatuss, TutorQuery tutorQuery, int type);

    /**
     * 根据applyId获取第一页信息
     * @param ApplyId
     * @return FirstPage
     */
    FirstPage getFirstPage(int ApplyId);

    /**
     * 更新第一页申请导师信息
     *
     * @param firstPage 信息
     * @return 修改条数
     */
    int saveTutorInspectBaseInfo(FirstPage firstPage);

    /**
     * 获取第二页的导师信息
     *
     * @param applyId tutor_inspect 表的主键
     * @return 第二页信息
     */
    SecondPage getTutorInspectSecond(int applyId);

    /**
     * 更新第二页的导师信息
     *
     * @param applyId    tutor_inspect 表的主键
     * @param secondPage 信息
     * @return 修改条数
     */
    int updateTutorInspectSecond(int applyId, SecondPage secondPage);

    /**
     * pdf获取
     * @param applyId
     * @return
     */
    PdfTutorInspect getPdfTutorInspect(int applyId);

}
