package com.nwu.controller;

import com.nwu.results.Result;
import com.nwu.results.ResultCode;
import com.nwu.service.PdfInspectService;
import com.nwu.service.PdfNoInspectService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("/tutor")
public class PDFController {

    @Resource
    PdfInspectService pdfInspectService;
    @Resource
    PdfNoInspectService pdfNoInspectService;

    @ApiOperation("pdf导出")
    @GetMapping("/getPdf/{applyId}/{applyTypeId}")
    public Result pdfCreate(@PathVariable("applyId") Integer applyId, @PathVariable("applyTypeId") Integer applyTypeId, HttpServletRequest request) throws Exception {
        if (applyId ==null && applyTypeId == null){
            return Result.FAIL();
        }
        //根据不同的申请类别读取不同的pdf资源文件
        String pdfTemplate = "";
        //开发版
        switch (applyTypeId){
            case 1: pdfTemplate = "pdfTemplates/firstApplyDoctor.pdf"; break;
            case 2: pdfTemplate = "pdfTemplates/addApplyDoctor.pdf"; break;
            case 3: pdfTemplate = "pdfTemplates/noInspectApplyDoctor.pdf"; break;
            case 4: pdfTemplate = "pdfTemplates/firstApplyMaster.pdf"; break;
            case 5: pdfTemplate = "pdfTemplates/addApplyMaster.pdf"; break;
            case 6: pdfTemplate = "pdfTemplates/noInspectApplyMaster.pdf"; break;
            case 7: pdfTemplate = "pdfTemplates/firstApplyProfessional.pdf"; break;
            case 8: pdfTemplate = "pdfTemplates/addApplyProfessional.pdf"; break;
            case 9: pdfTemplate = "pdfTemplates/noInspectApplyProfessional.pdf"; break;
        }
        String pdfPath = "";
        if (applyTypeId == 1 || applyTypeId ==2 || applyTypeId == 4 || applyTypeId ==5 || applyTypeId == 7 || applyTypeId == 8){
            //非免审
            pdfPath = pdfInspectService.getTutorInspectPdf(applyId, applyTypeId, pdfTemplate,request);
        }
        else
        {
            //免审
            pdfPath = pdfNoInspectService.getTutorNoInspect(applyId,applyTypeId,pdfTemplate,request);
        }
        HashMap<String, Object> map = new HashMap<>();
        if (!"".equals(pdfPath)){
            map.put("code",1201);
            map.put("pdfPath",pdfPath);
        }
        else {
            map.put("code",1200);
            map.put("pdfPath",pdfPath);
        }
        return new Result(ResultCode.SUCCESS,map);

//       linux版本
//       InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(pdfTemplate);

    }
}
