package com.nwu.util;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import com.nwu.entities.PdfEntity.PdfTable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

public class PDFTemplates {

    private String templatePdfPath;
    private String fontName = "simfang.ttf";


    public PDFTemplates(String templatePdfPath) {
        this.templatePdfPath = templatePdfPath;
    }

    public PDFTemplates(String templatePdfPath, String fontName) {
        this.templatePdfPath = templatePdfPath;
        this.fontName = fontName;
    }

    //创建单元格
    private static PdfPCell createCell(Object value, Font font , int align){
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
        pdfPCell.setHorizontalAlignment(align); //水平居中
        pdfPCell.setPhrase(new Phrase(getBlank(value),font));   //参数：”字符串“，”字体样式“  设置短句（两行之间的间距）
        return pdfPCell;
    }

    private static PdfPCell createCell(Object value, Font font) {
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        pdfPCell.setPhrase(new Phrase(1f,getBlank(value), font));   //leading行间距
        return pdfPCell;
    }

    //读入的字符串
    public static String getBlank(Object value){
        if (value != null){
            return value.toString();
        }
        return "";
    }
    //本机字体路径
    private static String getFontPath(String fontName){
        String fontPath = "C:\\Windows\\Fonts\\" + fontName;
        //判断系统类型
        Properties properties = System.getProperties();
        String osName = properties.getProperty("os.name").toLowerCase();
        if (osName.indexOf("linux") > -1){
            fontPath = "/usr/share/fonts/" + fontName;
        }
        return fontPath;
    }

    //textFields表单字段（数据库获得） outputStream写入到表单里的字段值 表单下创建表格tableFields
    public void export(OutputStream outputStream, Map<String,Object> textFields, Map<String, PdfTable> tableFields,Map<String,Object> imgFields) throws IOException, DocumentException {

        PdfReader pdfReader = new PdfReader(templatePdfPath);   //读取pdf模板
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();  //输出流
        PdfStamper pdfStamper = new PdfStamper(pdfReader,byteArrayOutputStream);    //pdf修改器

        BaseFont baseFont = BaseFont.createFont(getFontPath(fontName), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);//输出中文字体
        ArrayList<BaseFont> fontList = new ArrayList<>();   //字体列表
        fontList.add(baseFont); //添加字体到列表
        AcroFields acroFields = pdfStamper.getAcroFields(); //获取编码类型允许设置文件的值,修改属性值
        acroFields.setSubstitutionFonts(fontList);     //文件设置可以替代的字体类型

        //textFields.entrySet()将Map集合转为Set集合，目的是使用后iterator()方法
        for (Map.Entry<String,Object> entry : textFields.entrySet()) {
            String key = entry.getKey();    //得到键
            Object value = entry.getValue();    //得到值
            Font font = new Font(baseFont, 12, Font.NORMAL);
            acroFields.setFieldProperty(key,"textfont",baseFont,null);
            acroFields.setField(key,getBlank(value));   //写入值
        }


            for (Map.Entry<String, Object> entry : imgFields.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().toString();
                //获取属性类型
                if(value != null && acroFields.getField(key) != null && !"".equals(value)){
                    AcroFields.FieldPosition fieldPosition = acroFields.getFieldPositions(key).get(0);
                    //获取图片的url
                    // Image image = Image.getInstance("http://10.8.47.148:8081/downFile/image/20133220.jpg");

                    String substring = value.substring(value.lastIndexOf('/'));

//                    Image image = Image.getInstance(DataUtils.imagePath + "\\" + substring);
                    //TODO 切换系统的路径斜杠
                    Image image = Image.getInstance(DataUtils.imagePath + "/" + substring);

//                BufferedImage bufferedImage = ImageIO.read(new File());
//                BufferedImage bufferedImage = new BufferedImage(75, 100, BufferedImage.TYPE_INT_RGB);

//                ByteArrayOutputStream os = new ByteArrayOutputStream();
//                ImageIO.write(bufferedImage, "jpg", os);
//                Image image = Image.getInstance(os.toByteArray());
                    PdfContentByte pdfContentByte = pdfStamper.getOverContent(1);   //pdf修改器
                    image.scaleAbsolute(75,100);    //宽高
                    float marginLeft = (fieldPosition.position.getRight() - fieldPosition.position.getLeft() - image.getScaledWidth())/2;   //左边距 = 右边距
                    image.setAbsolutePosition(fieldPosition.position.getLeft() + marginLeft,fieldPosition.position.getBottom());
                    pdfContentByte.addImage(image);

                }
            }

        //遍历图像字段


        //遍历表格字段    在表单里新加表格
        Font keyFont = new Font(baseFont,10,Font.BOLD);     //表头内容字体格式
        Font textFont = new Font(baseFont,10,Font.NORMAL);  //表格内容字体格式
        for (Map.Entry<String,PdfTable> entry: tableFields.entrySet()) {
            String key = entry.getKey();   //此处key为Pdf模板中对应的域名  ???????
            PdfTable pdfTable = entry.getValue();
            //获取属性类型
            if (pdfTable != null && pdfTable.getColFields() != null && acroFields.getField(key) != null){
                //获取位置
                AcroFields.FieldPosition fieldPosition = acroFields.getFieldPositions(key).get(0);
                float width = fieldPosition.position.getRight() - fieldPosition.position.getLeft();
                float height = fieldPosition.position.getTop() - fieldPosition.position.getBottom();
                //创建表格
                String[] thread = pdfTable.getColNames() != null ? pdfTable.getColNames().split(",") : pdfTable.getColFields().split(",");
                PdfPTable pdfPTable = new PdfPTable(thread.length); //创建表格的列数
                try {
                    pdfPTable.setTotalWidth(width); //宽
                    pdfPTable.setLockedWidth(true); //固定宽
                    pdfPTable.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中
                    pdfPTable.getDefaultCell().setBorder(1);    //设置单元格边框
                }catch (Exception e){
                    e.printStackTrace();
                }
                //创建表头 keyFont表头内容字体格式
                for (String col : thread ) {
                    pdfPTable.addCell(createCell(col,keyFont,Element.ALIGN_CENTER));
                }
                PdfContentByte pdfContentByte = pdfStamper.getOverContent(2);
                Integer n=0, m=0;
                //学术论文 tableAcademicPaper_lg tableAcademicPaper_ws
                if ((key == "tableAcademicPaper_lg") && (pdfPTable != null)){
                    //理工
                    float[] widths = { 0.04f,0.34f,0.07f,0.07f,0.08f,0.26f,0.05f,0.09f};
                    pdfPTable.setWidths(widths);
                    pdfContentByte = pdfStamper.getOverContent(2);
                    n = 6;
                    m= widths.length;
                }else if((key == "tableAcademicPaper_ws") && (pdfPTable != null)){
                    float[] widths = {0.04f, 0.31f, 0.14f, 0.15f, 0.3f, 0.06f};
                    pdfPTable.setWidths(widths);
                    pdfContentByte = pdfStamper.getOverContent(2);
                    n = 6;  //篇数
                    m = widths.length;
                }else if (key == "tableResearchProject"){   //科研项目
                    float[] widths = {0.04f, 0.25f, 0.11f, 0.10f, 0.10f, 0.10f, 0.1f, 0.1f,0.10f};
                    pdfPTable.setWidths(widths);
                    pdfContentByte = pdfStamper.getOverContent(3);
                    n = 5;
                    m = widths.length;
                }
                else if (key == "tableAcademicWorks"){      //教材或学术著作AcademicWorks
                    float[] widths = {0.06f, 0.3f, 0.06f, 0.12f, 0.26f, 0.1f,0.1f};
                    pdfPTable.setWidths(widths);
                    pdfContentByte = pdfStamper.getOverContent(3);
                    n = 2;
                    m = widths.length;
                }
                else if (key == "tableTeachingAwards"){     //科研教学奖励
                    float[] widths = {0.06f, 0.3f, 0.1f, 0.2f, 0.1f, 0.1f, 0.14f};
                    pdfPTable.setWidths(widths);
                    pdfContentByte = pdfStamper.getOverContent(3);
                    n = 2;
                    m = widths.length;
                }
                else if (key == "tableInventionPatent"){    //发明专利
                    float[] widths = {0.06f, 0.3f, 0.1f, 0.18f, 0.2f, 0.16f};
                    pdfPTable.setWidths(widths);
                    pdfContentByte = pdfStamper.getOverContent(3);
                    n = 2;
                    m = widths.length;
                }
                else if ((key == "tableGuidingDoctor") || (key == "tableGuidingMaster")){      //指导博士 指导硕士
                    float[] widths = {0.2f, 0.5f, 0.15f, 0.15f};
                    pdfPTable.setWidths(widths);
                    pdfContentByte = pdfStamper.getOverContent(4);
                    n = 3;
                    m = widths.length;
                }
                else if ((key == "tableHelpGuidingDoctor") ||(key == "tableHelpGuidingMaster")){  //协助指导博士 协助指导硕士
                    float[] widths = {0.2f, 0.35f, 0.15f, 0.15f, 0.15f};
                    pdfPTable.setWidths(widths);
                    pdfContentByte = pdfStamper.getOverContent(4);
                    n = 3;
                    m = widths.length;
                }
                else if (key == "tableGuidingBachelor"){     //指导本科
                    float[] widths = {0.2f, 0.2f, 0.6f};
                    pdfPTable.setWidths(widths);
                    pdfContentByte = pdfStamper.getOverContent(4);
                    n = 3;
                    m = widths.length;
                }
                else if (key == "tableCourseTeaching"){      //研究生课程教学情况
                    float[] widths = {0.2f, 0.4f, 0.15f, 0.25f};
                    pdfPTable.setWidths(widths);
                    pdfContentByte = pdfStamper.getOverContent(4);
                    n = 3;
                    m = widths.length;
                }
                else if ( key == "tableNoResearchProjects"){  //科研项目
                    float[] widths = {0.06f, 0.3f, 0.1f, 0.18f, 0.2f, 0.16f };
                    pdfPTable.setWidths(widths);
                    pdfContentByte = pdfStamper.getOverContent(2);
                    n=5;
                    m=widths.length;
                }
                else if (key == "tableNoTeachingAwards") {    //教学奖励
                    float[] widths = {0.06f, 0.25f, 0.15f, 0.18f, 0.2f, 0.16f}   ;
                    pdfPTable.setWidths(widths);
                    pdfContentByte = pdfStamper.getOverContent(2);
                    n=3;
                    m=widths.length;
                }


                //创建表体
                String[] fields = pdfTable.getColFields().split(",");
                List<Map<String, Object>> dataList = pdfTable.getDataList();    //dataList论文篇数
                if (dataList != null && dataList.size()>0){
                    float height_row = 0;
                    float height_sum = 0;
                    for (int i = 0; i < n; i++){
                        if(i < dataList.size()){
                            Map<String, Object> row = dataList.get(i);
                            for (String field: fields) {
                                //createCell(表格域，字体)
                                pdfPTable.addCell(createCell(row.get(field),textFont));
                            }
                            height_row = pdfPTable.getRowHeight(i+1);
                            height_sum = height_sum + height_row;   //获取表格body部分填写内容后的高度和（不含表头）
                        }
                        else if(i>= dataList.size()){
                            PdfPCell cell;
                            cell= new PdfPCell(new Phrase(" ",textFont));
                            cell.setColspan(1);
                            cell.setRowspan(1);
                            float height_cell = 0;
                            for (int j = 0;j<m;j++){
                                pdfPTable.addCell(cell);
                                //总高度height减去height_sum后还需减去表头（第0行）的高度才是剩余空白部分的总高度
                                height_cell =  (height - height_sum - pdfPTable.getRowHeight(0))/(n-dataList.size());
                                cell.setFixedHeight(height_cell);
                            }
                        }
                    }
                }

                //插入内容
                pdfPTable.writeSelectedRows(0,-1,0,-1,fieldPosition.position.getLeft(),fieldPosition.position.getTop(),pdfContentByte);
            }
        }
        pdfStamper.setFormFlattening(true);
        pdfStamper.close();
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.flush();
        outputStream.close();

        byteArrayOutputStream.close();
        pdfReader.close();

    }
}
