package com.nwu.controller.scientificResearchManager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nwu.entities.tutor.TeacherInfo;
import com.nwu.results.Result;
import com.nwu.results.ResultCode;
import com.nwu.service.tutor.common.TeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/teacher_info")
public class TeacherInfoController {

    @Autowired
    TeacherInfoService teacherInfoService;

    @GetMapping("/get/{ZGH}")
    public Result getAll(@PathVariable("ZGH") String ZGH){
        QueryWrapper<TeacherInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("ZGH",ZGH);
        List<TeacherInfo> list = teacherInfoService.list(wrapper);
        System.out.println("------------------教师信息TeacherInfo------------------");
        System.out.println(list);
        return new Result(ResultCode.SUCCESS,list);
    }



}
