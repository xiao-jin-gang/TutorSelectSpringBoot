package com.nwu.service.tutor.common.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.entities.tutor.childSubject.GuidingStudent;
import com.nwu.mapper.tutor.common.GuidingStudentMapper;
import com.nwu.service.tutor.common.GuidingStudentService;
import org.springframework.stereotype.Service;

/**
 * @author Rex Joush
 * @time 2021.08.30 21:40
 */
@Service
public class GuidingStudentServiceImpl extends ServiceImpl<GuidingStudentMapper, GuidingStudent> implements GuidingStudentService {
}
