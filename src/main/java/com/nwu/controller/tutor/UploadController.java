//package com.nwu.controller.tutor.common;
//
///**
// * @author Rex Joush
// * @time 2021.08.25 19:41
// */
//
//import com.nwu.results.Result;
//import com.nwu.results.ResultCode;
////import com.nwu.util.FtpUtil;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.annotation.Resource;
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 文件的上传控制器
// */
//@RestController
//@RequestMapping("/tutor")
//public class UploadController {
//
//    @Resource
//    private FtpUtil ftpUtil;
//
//    @PostMapping("/upload/{filetype}")
//    public Result upload(@RequestParam("material") MultipartFile materials,
//                         @PathVariable("filetype") int filetype) throws IOException {
//
//        System.out.println(filetype);
//        /*
//            filetype 添加的文件类型
//                1, 社科论文
//                2, 理工论文
//                3, 科研项目
//                4, 教材或学术著作
//                5, 科研或教学奖励
//                6, 发明专利
//         */
//        String url = "/joush";
//        switch (filetype) {
//            case 1:
//            case 2: url += "/academicPapers/"; break;
//            case 3: url += "/researchProjects/"; break;
//            case 4: url += "/academicWorks/"; break;
//            case 5: url += "/teachingAwards/"; break;
//            case 6: url += "/inventionPatents/"; break;
//        }
//        // 接收到文件
//        if (!materials.isEmpty()){
//            System.out.println(materials.getOriginalFilename());
//            // materials.transferTo(new File("D:\\cache\\" + materials.getOriginalFilename()));
////            ftpUtil.uploadFile(materials.getOriginalFilename(), materials.getInputStream());//主要就是这里实现了ftp的文件上传
//        }
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("fileType", filetype);
//        result.put("url", "https://www.rexjoush.com/file/111.zip");
//
//        return new Result(ResultCode.SUCCESS, result);
//    }
//
//}
