package com.nwu.service.tutor.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nwu.entities.Apply;
import com.nwu.entities.Organization;
import com.nwu.entities.tutor.FirstPage;
import com.nwu.mapper.TutorInspectMapper;
import com.nwu.mapper.tutor.common.TutorApplyMapper;
import com.nwu.service.OrganizationService;
import com.nwu.service.tutor.common.FirstService;
import com.nwu.util.SaveImage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class FirstServiceImpl implements FirstService {

    @Resource
    private TutorApplyMapper tutorApplyMapper;

    @Resource
    private OrganizationService organizationService;

    @Resource
    private TutorInspectMapper tutorInspectMapper;

    @Override
    public int saveApplyInfo(Apply apply) {
        return tutorApplyMapper.saveApplyInfo(apply);
    }

    @Override
    public int updateFirstPage(String applyId, String phone, String email, String evaluateTime, String awardingUnitTime) {
        return tutorInspectMapper.updateFirstPage(applyId, phone, email, evaluateTime, awardingUnitTime);
    }

    @Override
    public int saveFirstPage(FirstPage firstPage, HttpServletRequest request) {
        int i = 0;
        try {
            // 添加教师基本表 院系名字
            QueryWrapper<Organization> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("organization_name", firstPage.getOrganizationName());
            Organization one = organizationService.getOne(queryWrapper);

            // 设置院系 id
            firstPage.setOrganizationId(one.getOrganizationId());
            if (!"".equals(firstPage.getAwardDepartment())) {
                // 拼接授予时间及单位
                firstPage.setAwardingUnitTime(firstPage.getAwardDepartment() + " " + firstPage.getAwardTime());
            } else {
                firstPage.setAwardingUnitTime("");
            }

            // 将图路径存入数据库
            String httpPath = SaveImage.ExportBlob(firstPage.getBlobImage(), firstPage.getTutorId(), request);
            firstPage.setImage(httpPath);
            i = tutorInspectMapper.saveTutorInspectBaseInfo(firstPage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("网络异常，请稍后再试" + "!" + e.getMessage());
        }
        return i;
    }
}
