package com.nwu.service.tutor.common.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.entities.tutor.childSubject.CourseTeaching;
import com.nwu.mapper.tutor.common.CourseTeachingMapper;
import com.nwu.service.tutor.common.CourseTeachingService;
import org.springframework.stereotype.Service;

/**
 * @author Rex Joush
 * @time 2021.08.30 21:38
 */
@Service
public class CourseTeachingServiceImpl extends ServiceImpl<CourseTeachingMapper, CourseTeaching> implements CourseTeachingService {
}
