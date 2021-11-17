package com.nwu.service.tutor.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.entities.Summary;
import com.nwu.mapper.tutor.SummaryMapper;
import com.nwu.service.tutor.SummaryService;
import org.springframework.stereotype.Service;

/**
 * @author Rex Joush
 * @time 2021.08.29 21:10
 */
@Service
public class SummaryServiceImpl extends ServiceImpl<SummaryMapper, Summary> implements SummaryService {
}
