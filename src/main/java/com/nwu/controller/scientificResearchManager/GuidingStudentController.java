package com.nwu.controller.scientificResearchManager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nwu.entities.tutor.childSubject.GuidingStudent;
import com.nwu.results.Result;
import com.nwu.results.ResultCode;
import com.nwu.service.tutor.common.GuidingStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/guiding_student")
public class GuidingStudentController {

    @Autowired
    GuidingStudentService guidingStudentService;

    @GetMapping("/get/{tutorId}/{applyId}")
    public Result getAll(@PathVariable("tutorId") String tutorId,
                         @PathVariable String applyId){
        QueryWrapper<GuidingStudent> wrapper = new QueryWrapper<>();
        wrapper.eq("tutor_id",tutorId).eq("apply_id",applyId);
        List<GuidingStudent> list = guidingStudentService.list(wrapper);
        System.out.println(list);
        return new Result(ResultCode.SUCCESS,list);
    }

}
