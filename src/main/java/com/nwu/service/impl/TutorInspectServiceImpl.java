package com.nwu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nwu.entities.ApplyInfo;
import com.nwu.entities.Organization;
import com.nwu.entities.PdfEntity.PdfTutorInspect;
import com.nwu.entities.Summary;
import com.nwu.entities.TutorInspect;
import com.nwu.entities.tutor.FirstPage;
import com.nwu.entities.tutor.SecondPage;
import com.nwu.mapper.TutorInspectMapper;
import com.nwu.service.OrganizationService;
import com.nwu.service.TutorInspectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.service.tutor.SummaryService;
import com.nwu.vo.QueryDepartmentSecretaryInit;
import com.nwu.vo.TutorQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dynamic
 * @since 2021-08-09
 */
@Service
public class TutorInspectServiceImpl extends ServiceImpl<TutorInspectMapper, TutorInspect> implements TutorInspectService {

    @Resource
    private TutorInspectMapper tutorInspectMapper;

    @Resource
    private OrganizationService organizationService;

    @Resource
    private SummaryService summaryService;

    @Override
    public FirstPage getFirstPage(int applyId) {

        FirstPage firstPage = null;
        try {
            firstPage = tutorInspectMapper.getFirstPage(applyId);
            // 查询所在院系
            QueryWrapper<Organization> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("organization_id", firstPage.getOrganizationId());
            Organization one = organizationService.getOne(queryWrapper);
            firstPage.setOrganizationName(one.getOrganizationName());

            // 设置对应的授予单位及时间
            if (!"".equals(firstPage.getAwardingUnitTime()) && firstPage.getAwardingUnitTime() != null) {
                firstPage.setAwardDepartment(firstPage.getAwardingUnitTime().split(" ")[0]);
                firstPage.setAwardTime(firstPage.getAwardingUnitTime().split(" ")[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取教师信息失败" + "!" + e.getMessage());
        }
        return firstPage;
    }

    @Override
    public List<QueryDepartmentSecretaryInit> getTutorInitOrSearch(int organizationId, List<String> applyStatuss, int pageNumber, TutorQuery tutorQuery, int type) {

        List<QueryDepartmentSecretaryInit> tutorInspect = new ArrayList<>();
        List<ApplyInfo> applyInfos;

        try {
            if (type == 0) {
                // 初始化获取
                applyInfos = tutorInspectMapper.getTutorInspectApplyInit(organizationId, applyStatuss, (pageNumber - 1) * 10);
            } else {
                // 查询获取
                tutorQuery.setUserName("%" + tutorQuery.getUserName() + "%");
                applyInfos = tutorInspectMapper.getTutorInspectApplySearch(tutorQuery, (pageNumber - 1) * 10);
            }

            for (ApplyInfo applyInfo : applyInfos) {
                QueryDepartmentSecretaryInit tutorInspectApplyInitDetails = null;

                // 学硕博导免审
                if (applyInfo.getApplyTypeId() == 3 || applyInfo.getApplyTypeId() == 6) {
                    tutorInspectApplyInitDetails = tutorInspectMapper.getTutorNoInspectApplyInitDetails(applyInfo.getApplyId());

                    // 设置申请的学院和专业
                    if (!"".equals(tutorInspectApplyInitDetails.getAppliedSubjectUnit()) && tutorInspectApplyInitDetails.getAppliedSubjectUnit() != null) {
                        tutorInspectApplyInitDetails.setApplyDepartment(tutorInspectApplyInitDetails.getAppliedSubjectUnit());
                    } else {
                        tutorInspectApplyInitDetails.setApplyDepartment("");
                    }

                    if (!"".equals(tutorInspectApplyInitDetails.getAppliedSubjectCode()) && tutorInspectApplyInitDetails.getAppliedSubjectCode() != null) {
                        tutorInspectApplyInitDetails.setApplySubject(tutorInspectApplyInitDetails.getAppliedSubjectCode() + " " + tutorInspectApplyInitDetails.getAppliedSubjectName());
                    } else {
                        tutorInspectApplyInitDetails.setApplySubject("");
                    }

                    // 标识免审
                    tutorInspectApplyInitDetails.setNoInspect(true);
                }
                // 专硕免审
                else if (applyInfo.getApplyTypeId() == 9) {
                    tutorInspectApplyInitDetails = tutorInspectMapper.getTutorNoInspectApplyInitDetails(applyInfo.getApplyId());
                    if (tutorInspectApplyInitDetails.getProfessionalApplicationSubjectCode() == null) {
                        tutorInspectApplyInitDetails.setApplySubject("");
                    } else {
                        if (tutorInspectApplyInitDetails.getProfessionalFieldCode() == null) {
                            tutorInspectApplyInitDetails.setApplySubject(tutorInspectApplyInitDetails.getProfessionalApplicationSubjectCode() + " " + tutorInspectApplyInitDetails.getProfessionalApplicationSubjectName());
                        } else {
                            tutorInspectApplyInitDetails.setApplySubject(tutorInspectApplyInitDetails.getProfessionalFieldCode() + " " + tutorInspectApplyInitDetails.getProfessionalFieldName());
                        }
                    }
                    tutorInspectApplyInitDetails.setNoInspect(true);
                }
                // 非免审
                else {
                    tutorInspectApplyInitDetails = tutorInspectMapper.getTutorInspectApplyInitDetails(applyInfo.getApplyId());

                    switch (tutorInspectApplyInitDetails.getApplyTypeId()) {
                        case 1:
                        case 2:
                        case 4:
                        case 5:
                            tutorInspectApplyInitDetails.setApplyDepartment(tutorInspectApplyInitDetails.getDoctoralMasterApplicationSubjectUnit());
                            if (tutorInspectApplyInitDetails.getDoctoralMasterSubjectCode() == null) {
                                tutorInspectApplyInitDetails.setApplySubject("");
                            } else {
                                tutorInspectApplyInitDetails.setApplySubject(tutorInspectApplyInitDetails.getDoctoralMasterSubjectCode() + " " + tutorInspectApplyInitDetails.getDoctoralMasterSubjectName());
                            }
                            break;
                        case 7:
                        case 8:
                            tutorInspectApplyInitDetails.setApplyDepartment(tutorInspectApplyInitDetails.getProfessionalApplicationSubjectUnit());
                            if (tutorInspectApplyInitDetails.getProfessionalApplicationSubjectCode() == null) {
                                tutorInspectApplyInitDetails.setApplySubject("");
                            } else {
                                if (tutorInspectApplyInitDetails.getProfessionalFieldCode() == null) {
                                    tutorInspectApplyInitDetails.setApplySubject(tutorInspectApplyInitDetails.getProfessionalApplicationSubjectCode() + " " + tutorInspectApplyInitDetails.getProfessionalApplicationSubjectName());
                                } else {
                                    tutorInspectApplyInitDetails.setApplySubject(tutorInspectApplyInitDetails.getProfessionalFieldCode() + " " + tutorInspectApplyInitDetails.getProfessionalFieldName());
                                }
                            }
                            break;
                        default:
                            break;
                    }
                    // 标识非免审
                    tutorInspectApplyInitDetails.setNoInspect(false);

                    // 获取汇总信息
                    QueryWrapper<Summary> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("tutor_id", tutorInspectApplyInitDetails.getTutorId());
                    queryWrapper.eq("apply_id", tutorInspectApplyInitDetails.getApplyId());
                    Summary summary = summaryService.getOne(queryWrapper);

                    tutorInspectApplyInitDetails.setSummary(summary.getSummaryString());
                }

                tutorInspect.add(tutorInspectApplyInitDetails);

            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取数据失败，请稍后再试" + "!" + e.getMessage());
        }

        // 返回合并后的列表
        return tutorInspect;
    }

    @Override
    public int getTutorInitOrSearchTotal(int organizationId, List<String> applyStatuss, TutorQuery tutorQuery, int type) {
        int total;
        try {
            if (type == 0) {
                // 初始化获取
                total = tutorInspectMapper.getTutorInspectApplyInitTotal(organizationId, applyStatuss);
            } else {
                // 查询获取
                total = tutorInspectMapper.getTutorInspectApplySearchTotal(tutorQuery);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取数据失败，请稍后再试" + "!" + e.getMessage());
        }
        return total;
    }

    @Override
    public List<QueryDepartmentSecretaryInit> exportTutorInitOrSearch(int organizationId, List<String> applyStatuss, TutorQuery tutorQuery, int type) {
        List<QueryDepartmentSecretaryInit> tutorInspectInit = null;
        List<QueryDepartmentSecretaryInit> tutorNoInspectInit = null;

        try {
            // 获取非免审的信息
            if (type == 0) {
                // 非条件查询
                tutorInspectInit = tutorInspectMapper.exportTutorInspectInit(organizationId, applyStatuss);
            } else {
                // 条件查询
                tutorInspectInit = tutorInspectMapper.exportTutorInspectSearch(tutorQuery);
            }
            for (QueryDepartmentSecretaryInit queryDepartmentSecretaryInit : tutorInspectInit) {
                switch (queryDepartmentSecretaryInit.getApplyTypeId()) {
                    case 1:
                    case 2:
                    case 4:
                    case 5:
                        queryDepartmentSecretaryInit.setApplyDepartment(queryDepartmentSecretaryInit.getDoctoralMasterApplicationSubjectUnit());
                        if (queryDepartmentSecretaryInit.getDoctoralMasterSubjectCode() == null) {
                            queryDepartmentSecretaryInit.setApplySubject("");
                        } else {
                            queryDepartmentSecretaryInit.setApplySubject(queryDepartmentSecretaryInit.getDoctoralMasterSubjectCode() + " " + queryDepartmentSecretaryInit.getDoctoralMasterSubjectName());
                        }
                        break;
                    case 7:
                    case 8:
                        queryDepartmentSecretaryInit.setApplyDepartment(queryDepartmentSecretaryInit.getProfessionalApplicationSubjectUnit());
                        if (queryDepartmentSecretaryInit.getProfessionalApplicationSubjectCode() == null) {
                            queryDepartmentSecretaryInit.setApplySubject("");
                        } else {
                            if (queryDepartmentSecretaryInit.getProfessionalFieldCode() == null) {
                                queryDepartmentSecretaryInit.setApplySubject(queryDepartmentSecretaryInit.getProfessionalApplicationSubjectCode() + " " + queryDepartmentSecretaryInit.getProfessionalApplicationSubjectName());
                            } else {
                                queryDepartmentSecretaryInit.setApplySubject(queryDepartmentSecretaryInit.getProfessionalFieldCode() + " " + queryDepartmentSecretaryInit.getProfessionalFieldName());
                            }
                        }
                        break;
                    default:
                        break;
                }
                // 标识非免审
                queryDepartmentSecretaryInit.setNoInspect(false);
                // 获取汇总信息
                QueryWrapper<Summary> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("tutor_id", queryDepartmentSecretaryInit.getTutorId());
                queryWrapper.eq("apply_id", queryDepartmentSecretaryInit.getApplyId());
                Summary summary = summaryService.getOne(queryWrapper);

                queryDepartmentSecretaryInit.setSummary(summary.getSummaryString());
                queryDepartmentSecretaryInit.setPaper(summary.getSummaryAcademicPaperString());
                queryDepartmentSecretaryInit.setWork(summary.getSummaryAcademicWorksString());
                queryDepartmentSecretaryInit.setAwards(summary.getSummaryTeachingAwardsString());
                queryDepartmentSecretaryInit.setInvention(summary.getSummaryInventionPatentsString());
                queryDepartmentSecretaryInit.setProject(summary.getSummaryResearchProjectsString());
            }

            // 获取免审的信息
            if (type == 0) {
                tutorNoInspectInit = tutorInspectMapper.exportTutorNoInspectInit(organizationId, applyStatuss);
            } else {
                tutorNoInspectInit = tutorInspectMapper.exportTutorNoInspectSearch(tutorQuery);
            }
            // 标记免审
            tutorNoInspectInit.forEach(queryDepartmentSecretaryInit -> queryDepartmentSecretaryInit.setNoInspect(true));

            for (QueryDepartmentSecretaryInit queryDepartmentSecretaryInit : tutorNoInspectInit) {
                // 设置申请的学院和专业
                if (!"".equals(queryDepartmentSecretaryInit.getAppliedSubjectUnit()) && queryDepartmentSecretaryInit.getAppliedSubjectUnit() != null) {
                    queryDepartmentSecretaryInit.setApplyDepartment(queryDepartmentSecretaryInit.getAppliedSubjectUnit());
                } else {
                    queryDepartmentSecretaryInit.setApplyDepartment("");
                }
                if (!"".equals(queryDepartmentSecretaryInit.getAppliedSubjectCode()) && queryDepartmentSecretaryInit.getAppliedSubjectCode() != null) {
                    queryDepartmentSecretaryInit.setApplySubject(queryDepartmentSecretaryInit.getAppliedSubjectCode() + " " + queryDepartmentSecretaryInit.getAppliedSubjectName());
                } else {
                    queryDepartmentSecretaryInit.setApplySubject("");
                }
                // 标识免审
                queryDepartmentSecretaryInit.setNoInspect(true);
            }
        } catch (Exception e) {
            throw new RuntimeException("获取数据失败，请稍后再试" + "!" + e.getMessage());
        }
        // 返回合并后的列表
        return Stream.of(tutorInspectInit, tutorNoInspectInit).flatMap(Collection::stream).collect(Collectors.toList());
    }


    @Override
    public int saveTutorInspectBaseInfo(FirstPage firstPage) {

        return tutorInspectMapper.saveTutorInspectBaseInfo(firstPage);
    }

    @Override
    public SecondPage getTutorInspectSecond(int applyId) {

        return tutorInspectMapper.getTutorInspectSecond(applyId);
    }

    @Override
    public int updateTutorInspectSecond(int applyId, SecondPage secondPage) {
        return tutorInspectMapper.updateTutorInspectSecond(applyId, secondPage);
    }

    /**
     * pdf打印
     * @param applyId
     * @return
     */
    @Override
    public PdfTutorInspect getPdfTutorInspect(int applyId) {
        return tutorInspectMapper.getTutorInspect(applyId);
    }

}
