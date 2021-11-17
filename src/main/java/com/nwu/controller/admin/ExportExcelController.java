package com.nwu.controller.admin;

import com.nwu.service.admin.impl.ExportExcelServiceImpl;
import com.nwu.service.impl.TutorInspectServiceImpl;
import com.nwu.util.exportExcel.*;
import com.nwu.vo.QueryDepartmentSecretaryInit;
import com.nwu.vo.TutorQuery;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
*  导出excel
*/

@RestController
@RequestMapping("/admin/export")
public class ExportExcelController {

    @Resource
    private ExportExcelServiceImpl exportExcelService;

    @Resource
    private TutorInspectServiceImpl tutorInspectService;

    /**
     * 院系秘书初审页面导出所有数据
     * @param response
     * @param tutorQuery 封装对象
     * @throws IOException
     */
    @PostMapping("/cs")
    public void exportSc(HttpServletResponse response, @RequestBody TutorQuery tutorQuery) throws IOException {
        System.out.println(tutorQuery);
        // 1.查询数据
        List<QueryDepartmentSecretaryInit> list = tutorInspectService.exportTutorInitOrSearch(tutorQuery.getOrganization(), tutorQuery.getApplyStatuss(), tutorQuery, 1);
        System.out.println(list);
        // 2.输出Excel
        try {
            new FirstInspectExportExcel(response, "西北大学", tutorQuery.getOrganizationName(), list).execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 院系秘书导出同意上分会数据
     * @param response
     * @param tutorQuery 封装对象
     * @throws IOException
     */
    @PostMapping("/sfh")
    public void exportSFH(HttpServletResponse response, @RequestBody TutorQuery tutorQuery) throws IOException {
        System.out.println(tutorQuery);
        // 1.查询数据
        List<QueryDepartmentSecretaryInit> list = tutorInspectService.exportTutorInitOrSearch(tutorQuery.getOrganization(), tutorQuery.getApplyStatuss(), tutorQuery, 1);
        System.out.println(list);
        // 2.输出Excel
        try {
            new RecommendExportExcel(response, "西北大学", tutorQuery.getOrganizationName(), list).execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 院系秘书导出同意上学院分会数据
     * @param response
     * @param tutorQuery 封装对象
     * @throws IOException
     */

    @PostMapping("/sxyfh")
    public void exportSXYFH(HttpServletResponse response,@RequestBody TutorQuery tutorQuery) throws IOException {
        //1.查询数据
        List<QueryDepartmentSecretaryInit> list = tutorInspectService.exportTutorInitOrSearch(tutorQuery.getOrganization(), tutorQuery.getApplyStatuss(), tutorQuery, 1);
        //2.输出Excel

        try {
            new DeliberationExportExcel(response, "西北大学", tutorQuery.getOrganizationName(), list).execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return;
    }

    /**
     * 院系秘书导出该院最后通过名单
     * * @param response
     * @param tutorQuery 封装对象
     * @throws IOException
     */
    @PostMapping("/finalOrg")
    public void exportFinalDepartment(HttpServletResponse response,@RequestBody TutorQuery tutorQuery) throws IOException {
        //1.查询数据
        List<QueryDepartmentSecretaryInit> list = tutorInspectService.exportTutorInitOrSearch(tutorQuery.getOrganization(), tutorQuery.getApplyStatuss(), tutorQuery, 1);
        //2.输出Excel
        try {
            new FinalExportExcelDepartment(response, "西北大学", tutorQuery.getOrganizationName(), list).execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return;
    }

    /**
     * 研究生院上校会导出excel
     * @param response
     * @param tutorQuery 封装对象
     * @throws IOException
     */
    @PostMapping ("/qualification")
    public void qualificationExportExcel(HttpServletResponse response,@RequestBody TutorQuery tutorQuery) throws IOException{
        List<QueryDepartmentSecretaryInit> list = tutorInspectService.exportTutorInitOrSearch(tutorQuery.getOrganization(), tutorQuery.getApplyStatuss(), tutorQuery, 1);
        try {
            new QualificationExamExportExcel(response,"西北大学",list).execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return;
    }

    /**
     * 研究生院导出最终名单excel
     * @param response
     * @param tutorQuery 封装对象
     * @throws IOException
     */
    @PostMapping ("/finalListGraduate")
    public void finalListExportExcelGraduate(HttpServletResponse response,@RequestBody TutorQuery tutorQuery) throws IOException{
        List<QueryDepartmentSecretaryInit> list = tutorInspectService.exportTutorInitOrSearch(tutorQuery.getOrganization(), tutorQuery.getApplyStatuss(), tutorQuery, 1);
        try {
            new FinalExportExcelGraduate(response,"西北大学",list).execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return;
    }

}
