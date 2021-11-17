package com.nwu.controller.scientificResearchManager;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nwu.entities.AcademicPaper;
import com.nwu.entities.TeachingAwards;
import com.nwu.results.Result;
import com.nwu.results.ResultCode;
import com.nwu.service.scientificResearchManager.TeachingAwardsService;
import com.nwu.vo.UpdateAward;
import com.nwu.vo.UpdatePaper;
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
@RequestMapping("/admin/teaching_awards")
public class TeachingAwardsController {
    @Autowired
    TeachingAwardsService teachingAwardsService;

    @GetMapping("/get/{tutorId}/{applyId}")
    public Result getAll(@PathVariable("tutorId") String tutorId,
                         @PathVariable String applyId){
        QueryWrapper<TeachingAwards> wrapper = new QueryWrapper<>();
        wrapper.eq("tutor_id",tutorId).eq("apply_id",applyId);;
        List<TeachingAwards> list = teachingAwardsService.list(wrapper);
        System.out.println(list);
        return new Result(ResultCode.SUCCESS,list);
    }

    @PostMapping("/update")
    public Result updateAward (@RequestBody UpdateAward updateAward) throws  Exception {
        System.out.println(updateAward);
        TeachingAwards teachingAwards =  new TeachingAwards();
        BeanUtils.copyProperties(updateAward, teachingAwards);
        teachingAwardsService.updateById(teachingAwards);
        return Result.SUCCESS();
    }

}

