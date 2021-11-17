package com.nwu.controller.scientificResearchManager;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nwu.entities.ResearchProject;
import com.nwu.results.Result;
import com.nwu.results.ResultCode;
import com.nwu.service.scientificResearchManager.ResearchProjectService;
import com.nwu.vo.UpdateProject;
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
 * @since 2021-08-27
 */
@RestController
@RequestMapping("/admin/research_project")
public class ResearchProjectController {

    @Autowired
    ResearchProjectService researchProjectService;

    @GetMapping("/get/{tutorId}/{applyId}")
    public Result getAll(@PathVariable("tutorId") String tutorId,
                         @PathVariable String applyId){
        QueryWrapper<ResearchProject> wrapper = new QueryWrapper<>();
        wrapper.eq("tutor_id",tutorId).eq("apply_id",applyId);;
        List<ResearchProject> list = researchProjectService.list(wrapper);
        System.out.println(list);
        return new Result(ResultCode.SUCCESS,list);
    }

    @PostMapping("/update")
    public Result updateProject(@RequestBody UpdateProject updateProject) throws Exception {
        System.out.println(updateProject);
        ResearchProject researchProject = new ResearchProject();
        BeanUtils.copyProperties(updateProject,researchProject);
        System.out.println("========================");
        System.out.println(researchProject);
        researchProjectService.updateById(researchProject);
        return Result.SUCCESS();
    }

}

