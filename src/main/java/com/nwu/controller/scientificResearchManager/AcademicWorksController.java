package com.nwu.controller.scientificResearchManager;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nwu.entities.AcademicWorks;
import com.nwu.results.Result;
import com.nwu.results.ResultCode;
import com.nwu.service.scientificResearchManager.AcademicWorksService;
import com.nwu.vo.UpdateWork;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Anna
 * @since 2021-08-26
 */
@RestController
@RequestMapping("/admin/academic_works")
public class AcademicWorksController {

    @Autowired
    AcademicWorksService academicWorksService;

    @GetMapping("/get/{tutorId}/{applyId}")
    public Result getAll(@PathVariable("tutorId") String tutorId,
                         @PathVariable String applyId){
        QueryWrapper<AcademicWorks> wrapper = new QueryWrapper<>();
        wrapper.eq("tutor_id", tutorId).eq("apply_id",applyId);;

        List<AcademicWorks> list = academicWorksService.list(wrapper);
        System.out.println(list);
        return new Result(ResultCode.SUCCESS,list);
    }

    @PostMapping("/update")
    public Result updateWork(@RequestBody UpdateWork updateWork) throws Exception {
        System.out.println(updateWork);
        AcademicWorks academicWorks = new AcademicWorks();
        BeanUtils.copyProperties(updateWork, academicWorks);
        academicWorksService.updateById(academicWorks);
        return Result.SUCCESS();
    }

}

