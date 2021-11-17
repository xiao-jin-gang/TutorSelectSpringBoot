package com.nwu.controller.scientificResearchManager;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nwu.entities.AcademicPaper;
import com.nwu.results.Result;
import com.nwu.results.ResultCode;
import com.nwu.service.scientificResearchManager.AcademicPaperService;
import com.nwu.vo.PaperQuery;
import com.nwu.vo.UpdatePaper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Anna
 * @since 2021-08-26
 */
@RestController
@RequestMapping("/admin/academic_paper")
public class AcademicPaperController {

    @Autowired
    AcademicPaperService academicPaperService;

        @GetMapping("/get/{tutorId}/{applyId}")
        public Result getAll(@PathVariable("tutorId") String tutorId,
                         @PathVariable String applyId){
        QueryWrapper<AcademicPaper> wrapper = new QueryWrapper<>();
        wrapper.eq("tutor_id",tutorId).eq("apply_id",applyId);
        List<AcademicPaper> list = academicPaperService.list(wrapper);
        System.out.println(list);
        return new Result(ResultCode.SUCCESS,list);
    }

    @PostMapping("/update")
    public Result updatePaper(@RequestBody UpdatePaper updatePaper) throws  Exception {
        System.out.println(updatePaper);
        AcademicPaper academicPaper = new AcademicPaper();
        BeanUtils.copyProperties(updatePaper, academicPaper);
        academicPaperService.updateById(academicPaper);
        return Result.SUCCESS();
    }


}

