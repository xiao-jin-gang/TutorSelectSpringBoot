package com.nwu.mapper.tutor;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nwu.entities.Summary;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Rex Joush
 * @time 2021.08.29 20:49
 */
/*
    第三页的汇总信息 mapper接口
 */
@Mapper
public interface SummaryMapper extends BaseMapper<Summary> {
}
