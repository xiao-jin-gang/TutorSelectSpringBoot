package com.nwu.mapper;

import com.nwu.entities.ApplyInfo;
import com.nwu.entities.PdfEntity.PdfTutorInspect;
import com.nwu.entities.TutorInspect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nwu.entities.tutor.FirstPage;

import com.nwu.vo.QueryDepartmentSecretaryInit;

import com.nwu.entities.tutor.SecondPage;
import com.nwu.vo.TutorQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  导师非免审信息表 Mapper 接口
 * </p>
 *
 * @author dynamic
 * @since 2021-08-09
 */
@Mapper
public interface TutorInspectMapper extends BaseMapper<TutorInspect> {


    /**
     * 获取所有初始化申请信息
     * @param organizationId 院系 id
     * @param applyStatuss 申请状态列表
     * @param pageStart limit 起始索引
     */
    List<ApplyInfo> getTutorInspectApplyInit(int organizationId, List<String> applyStatuss, int pageStart);
    int getTutorInspectApplyInitTotal(int organizationId, List<String> applyStatuss);

    /**
     * 获取所有搜索的申请信息
     * @param tutorQuery 搜索条件
     *        tutorQuery.userId 工号
     *        tutorQuery.userName 姓名
     *        tutorQuery.organization 院系 id
     *        tutorQuery.applyType 申请类别
     *        tutorQuery.subjectType 学科属性，文史 1，理工 2，交叉学科 3
     *        tutorQuery.applyStatuss 申请状态码列表
     * @param pageStart limit 起始索引
     */
    List<ApplyInfo> getTutorInspectApplySearch(TutorQuery tutorQuery, int pageStart);
    int getTutorInspectApplySearchTotal(TutorQuery tutorQuery);

    /**
     * 非免审 ---- 查询当前申请的详细信息，用于管理员页面的展示
     * @param applyId 申请 id
     * @return 申请详情
     */
    QueryDepartmentSecretaryInit getTutorInspectApplyInitDetails(int applyId);

    /**
     * 免审 ---- 查询当前申请的详细信息，用于管理员页面的展示
     * @param applyId 申请 id
     * @return 申请详情
     */
    QueryDepartmentSecretaryInit getTutorNoInspectApplyInitDetails(int applyId);


    /**
     * excel ---- 导出所有免审
     * @param organizationId 院系 id
     * @param applyStatuss 申请状态列表
     * @return 信息
     */
    List<QueryDepartmentSecretaryInit> exportTutorNoInspectInit(int organizationId, List<String> applyStatuss);

    /**
     * excel ---- 导出所有非免审
     * @param organizationId 院系 id
     * @param applyStatuss 申请状态列表
     * @return 信息
     */
    List<QueryDepartmentSecretaryInit> exportTutorInspectInit(int organizationId, List<String> applyStatuss);

    /**
     * excel ---- 导出查询非免审
     * @param tutorQuery 查询条件
     * @return 申请列表
     */
    List<QueryDepartmentSecretaryInit> exportTutorInspectSearch(TutorQuery tutorQuery);

    /**
     * excel ---- 导出查询免审
     * @param tutorQuery 查询条件
     * @return 申请列表
     */
    List<QueryDepartmentSecretaryInit> exportTutorNoInspectSearch(TutorQuery tutorQuery);

    /**
     * 保存第一页的 tutor_inspect 信息
     * @param firstPage 第一页信息
     */
    int saveTutorInspectBaseInfo(@Param("firstPage") FirstPage firstPage);

    /**
     * 获取第二页的申请信息
     * @param applyId 申请 id
     * @return 第二页信息详情
     */
    SecondPage getTutorInspectSecond(@Param("applyId") int applyId);

    /**
     * 更新第二页信息
     * @param applyId 申请 id
     * @param secondPage 第二页信息
     */
    int updateTutorInspectSecond(@Param("applyId") int applyId, @Param("secondPage") SecondPage secondPage);

    /**
     * 从 tutorInspect 获取第一页基本信息
     * @param applyId 申请 id
     * @return 第一页的信息
     */
    FirstPage getFirstPage(@Param("applyId") int applyId);

    /**
     * 更新第一页信息
     * @param applyId 申请 id
     * @param phone 电话
     * @param email 邮箱
     * @param evaluateTime 职称评定时间
     * @param awardingUnitTime 学位授予单位及时间
     */
    int updateFirstPage(String applyId, String phone, String email, String evaluateTime, String awardingUnitTime);

    /**
     * 获取 tutorInspect 表全部信息
     * @param applyId   申请主键
     * @return  PdfTutorInspect
     */
    PdfTutorInspect getTutorInspect(@Param("applyId") Integer applyId);

}
