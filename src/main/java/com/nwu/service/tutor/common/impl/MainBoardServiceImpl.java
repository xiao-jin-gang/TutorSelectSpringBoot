package com.nwu.service.tutor.common.impl;

import com.nwu.entities.Apply;
import com.nwu.mapper.tutor.common.TutorApplyMapper;
import com.nwu.service.tutor.common.MainBoardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class MainBoardServiceImpl implements MainBoardService {

    @Resource
    private TutorApplyMapper tutorApplyMapper;

    @Override
    public List<Apply> getApplyByTutorIdAndApplyTypeId(String tutorId, Integer applyTypeId) {
        return tutorApplyMapper.getApplyByTutorIdAndApplyTypeId(tutorId, applyTypeId);
    }

    @Override
    public int saveApplyInfo(Apply apply) {
        return tutorApplyMapper.saveApplyInfo(apply);
    }

    @Override
    public int updateApplySubject(int id, int applySubject) {
        return tutorApplyMapper.updateApplySubject(id, applySubject);
    }

    @Override
    public int getApplyId(String tutorId, int applyTypeId, int status) {
        return tutorApplyMapper.getApplyId(tutorId, applyTypeId, status);
    }
}
