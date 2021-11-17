package com.nwu.controller.common;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nwu.entities.SystemTime;
import com.nwu.results.Result;
import com.nwu.results.ResultCode;
import com.nwu.service.impl.SystemTimeServiceImpl;
import com.nwu.util.TimeUtils;
import com.sun.tools.jconsole.JConsoleContext;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.SybaseAnywhereMaxValueIncrementer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  系统时间前端控制器
 * </p>
 *
 * @author dynamic
 * @since 2021-08-09
 */
@RestController
@RequestMapping("/admin/system-time")
public class SystemTimeController {
    @Autowired
    private SystemTimeServiceImpl systemTimeService;
    @GetMapping("/save/{time}/{orgId}")
    public Result saveTime(@PathVariable String time,@PathVariable Integer orgId){
        System.out.println(time);
        String[] times = time.split(",");
        QueryWrapper<SystemTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("organization_id",orgId);
        SystemTime systemTime = systemTimeService.getOne(queryWrapper);
        boolean resSave =false;
        boolean resUpadte = false;
        if(systemTime!=null) {
            systemTime.setStartTime(times[0]);
            systemTime.setEndTime(times[1]);
            resUpadte = systemTimeService.update(systemTime,queryWrapper);
        }else{
            SystemTime time1 = new SystemTime();
            time1.setOrganizationId(orgId);
            time1.setStartTime(times[0]);
            time1.setEndTime(times[1]);
            resSave = systemTimeService.save(time1);
        }
        HashMap<String, Object> map = new HashMap<>();

        if(resSave){
            map.put("code",20002);
        }else if(resUpadte) {
            map.put("code",20000);
        }else{
            map.put("code",20001);
        }
        return new Result(ResultCode.SUCCESS,map);
    }
    @GetMapping("/get/{orgId}")
    public Result getTime(@PathVariable Integer orgId){

        SystemTime systemTime = new SystemTime();
        QueryWrapper<SystemTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("organization_id",orgId);
        SystemTime res = systemTimeService.getOne(queryWrapper);
        if (res == null) {
            return new Result(ResultCode.SUCCESS);
        } else {
            String start = res.getStartTime();
            String end = res.getEndTime();
            ArrayList<String> list = new ArrayList<>();
            list.add(start);
            list.add(end);
            return new Result(ResultCode.SUCCESS,list);
        }
    }
}

