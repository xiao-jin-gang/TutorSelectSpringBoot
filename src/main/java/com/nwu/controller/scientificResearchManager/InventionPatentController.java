package com.nwu.controller.scientificResearchManager;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nwu.entities.AcademicPaper;
import com.nwu.entities.InventionPatent;
import com.nwu.results.Result;
import com.nwu.results.ResultCode;
import com.nwu.service.scientificResearchManager.AcademicPaperService;
import com.nwu.service.scientificResearchManager.InventionPatentService;
import com.nwu.vo.UpdateInvention;
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
 * @since 2021-10-29
 */
@RestController
@RequestMapping("/admin/invention_patent")
public class InventionPatentController {

    @Autowired
    InventionPatentService inventionPatentService;

        @GetMapping("/get/{tutorId}/{applyId}")
        public Result getAll(@PathVariable("tutorId") String tutorId,
                         @PathVariable String applyId){
        QueryWrapper<InventionPatent> wrapper = new QueryWrapper<>();
        wrapper.eq("tutor_id",tutorId).eq("apply_id",applyId);
        List<InventionPatent> list = inventionPatentService.list(wrapper);
        System.out.println(list);
        return new Result(ResultCode.SUCCESS,list);
    }

    @PostMapping("/update")
    public Result updateInvention(@RequestBody UpdateInvention updateInvention) throws  Exception {
        System.out.println(updateInvention);
        InventionPatent inventionPatent = new InventionPatent();
        BeanUtils.copyProperties(updateInvention, inventionPatent);
        inventionPatentService.updateById(inventionPatent);
        return Result.SUCCESS();
    }


}

