package com.nwu.util.exportExcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import com.google.common.collect.Lists;
import com.nwu.vo.QueryDepartmentSecretaryInit;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
 *  学位评定分委员会审议汇总表
 */

public class DeliberationExportExcel {

    private List<QueryDepartmentSecretaryInit> originList;
    private HorizontalCellStyleStrategy horizontalCellStyleStrategy;
    private List<List<Object>> contentList = Lists.newArrayList();
    private HttpServletResponse response;
    private String schoolName;
    private String departmentName;
    private List<List<String>> headData;
    private String year;

    public DeliberationExportExcel(HttpServletResponse response,
                                   String schoolName,
                                   String departmentName,
                                   List<QueryDepartmentSecretaryInit> originList) {
        //1、生成年份
        Calendar instance = Calendar.getInstance();
        this.year = instance.get(Calendar.YEAR) + "";
        this.response = response;
        this.schoolName = schoolName;
        this.departmentName = departmentName;
        this.originList = originList;
    }


    public void execute() throws IOException {

        this.setHead(this.schoolName, this.departmentName);
        this.setResponse();
        this.exchangeData(this.originList);
        this.tableStyle();
        EasyExcel.write(response.getOutputStream())
                .head(this.headData).autoCloseStream(Boolean.FALSE)
                .registerWriteHandler(this.horizontalCellStyleStrategy)
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(18))
                .sheet().doWrite(contentList);
    }

    private void setResponse() throws UnsupportedEncodingException {

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String name = this.schoolName + this.year + "年" + this.departmentName + "学位评定分委员会推荐汇总表";
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8") + ".xlsx");

    }


    //表头
    private void setHead(String schoolName, String departmentName) {

        //2、构造表头
        List<List<String>> headTitles = Lists.newArrayList();
        String firstRow = schoolName + this.year + "年" + departmentName + "学位评定分委员会审议汇总表";
        String secondRow = "（首次上岗研究生导师/增列学科岗位认定）";
        String threeRow = "院（系、所）负责人签字：                                 填表人：                                  " +
                "                                                      年    月   日                                ";
        //第一列
        headTitles.add(Lists.newArrayList(firstRow, secondRow, threeRow, "序号", "序号", "序号"));
        //第2-6列
        ArrayList<String> basicInfoCol = Lists.newArrayList("工号", "姓名", "性别","联系方式","出生年月", "最后学位", "职称", "申请一级学科代码", "申请一级学科名称", "申请二级学科代码", "申请二级学科名称");
        basicInfoCol.forEach(title -> {
            headTitles.add(Lists.newArrayList(firstRow, secondRow, threeRow, "基本情况", title, title));
        });
        //第7列
        headTitles.add(Lists.newArrayList(firstRow, secondRow, threeRow, "导师上岗类型", "导师上岗类型", "导师上岗类型"));
        headTitles.add(Lists.newArrayList(firstRow, secondRow, threeRow, "科研情况汇总", "科研情况汇总", "科研情况汇总"));

//        //第8列
//        headTitles.add(Lists.newArrayList(firstRow, secondRow, threeRow, "科研情况", "学术论文（篇）", "SCI/权威"));
//        //第9列
//        headTitles.add(Lists.newArrayList(firstRow, secondRow, threeRow, "科研情况", "学术论文（篇）", "核心"));
//        //第10列
//        headTitles.add(Lists.newArrayList(firstRow, secondRow, threeRow, "科研情况", "科研项目（项）", "国家"));
//        //第11列
//        headTitles.add(Lists.newArrayList(firstRow, secondRow, threeRow, "科研情况", "科研项目（项）", "省部"));
//        //第12列
//        headTitles.add(Lists.newArrayList(firstRow, secondRow, threeRow, "科研情况", "科研经费（万元）", "纵向"));
//        //第13列
//        headTitles.add(Lists.newArrayList(firstRow, secondRow, threeRow, "科研情况", "科研经费（万元）", "横向"));
        //第14列
        headTitles.add(Lists.newArrayList(firstRow, secondRow, threeRow, "备注", "备注", "备注"));


        this.headData = headTitles;
    }

    //将数据库查出的符合前端规则的数据，转换成Excel表格的数据格式。
    public void exchangeData(List<QueryDepartmentSecretaryInit> list) {
        int i = 1;
        String primaryDisciplineCode = null;
        String PrimaryDisciplineName = null;
        for (QueryDepartmentSecretaryInit queryDepartmentSecretaryInit : list) {
            //判断学硕（博导）和专硕，一级学科字段不同
            if (queryDepartmentSecretaryInit.getApplyTypeId() > 6) {
                //专硕
                primaryDisciplineCode = queryDepartmentSecretaryInit.getProfessionalApplicationSubjectCode();// 专硕一级学科代码
                PrimaryDisciplineName = queryDepartmentSecretaryInit.getProfessionalApplicationSubjectName();//专硕一级学科名称
            } else {
                if(queryDepartmentSecretaryInit.getApplyTypeId() == 3 || queryDepartmentSecretaryInit.getApplyTypeId() == 6){
                    primaryDisciplineCode = queryDepartmentSecretaryInit.getAppliedSubjectCode();//一级学科代码
                    PrimaryDisciplineName = queryDepartmentSecretaryInit.getAppliedSubjectName();//一级学科名称
                }else {
                    primaryDisciplineCode = queryDepartmentSecretaryInit.getDoctoralMasterSubjectCode();//一级学科代码
                    PrimaryDisciplineName = queryDepartmentSecretaryInit.getDoctoralMasterSubjectName();//一级学科名称
                }
            }
            this.contentList.add(
                    Lists.newArrayList(
                            String.valueOf(i++),  //序号
                            queryDepartmentSecretaryInit.getTutorId(), //工号
                            queryDepartmentSecretaryInit.getName(),  //姓名
                            queryDepartmentSecretaryInit.getGender(),//性别
                            queryDepartmentSecretaryInit.getPhone(),//联系方式
                            queryDepartmentSecretaryInit.getBirthday(), //出生年月
                            queryDepartmentSecretaryInit.getFinalDegree(), //最后学位
                            queryDepartmentSecretaryInit.getTitle(), //职称
                            primaryDisciplineCode,
                            PrimaryDisciplineName,
                            queryDepartmentSecretaryInit.getProfessionalFieldCode(),//专硕二级代码
                            queryDepartmentSecretaryInit.getProfessionalFieldName(),//专硕二级名称
                            queryDepartmentSecretaryInit.getApplyName(),//导师上岗类别
                            queryDepartmentSecretaryInit.getSummary(),//科研情况汇总
//                            queryDepartmentSecretaryInit.getSsciAmount() + "/" + queryDepartmentSecretaryInit.getAuthorityAmount(),//SCI/权威   学术论文（篇）
//                            queryDepartmentSecretaryInit.getFirstAuthorPaper(),//核心  学术论文（篇）
//                            queryDepartmentSecretaryInit.getProjectNationalLevel(),//国家  科研项目（项）
//                            queryDepartmentSecretaryInit.getProjectProvinceLevel(),//省部  科研项目（项）
//                            queryDepartmentSecretaryInit.getAccumulatedFunds(),//纵向  科研经费（万元）
//                            queryDepartmentSecretaryInit.getHorizontalProject(),// 横向 科研经费（万元）
                            queryDepartmentSecretaryInit.getCommitYxXy()  //备注
                    )
            );
        }
    }

    //表的格式定义
    private void tableStyle() {
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //背景
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 12);
        headWriteCellStyle.setWriteFont(headWriteFont);

        //设置内容策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 设置自动换行
        contentWriteCellStyle.setWrapped(true);
        //设置 垂直居中
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//        //设置 水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 设置底边框;
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        // 设置底边框颜色;
        contentWriteCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        // 设置左边框;
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        // 设置左边框颜色;
        contentWriteCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        // 设置右边框;
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        // 设置右边框颜色;
        contentWriteCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        // 设置顶边框;
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        // 设置顶边框颜色;
        contentWriteCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

        //字体
//        WriteFont contentWriteFont = new WriteFont();
//        contentWriteFont.setFontHeightInPoints((short)10);
        this.horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }
}
