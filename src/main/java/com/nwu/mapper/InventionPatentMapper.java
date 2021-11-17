package com.nwu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nwu.entities.InventionPatent;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Rex Joush
 * @time 2021.08.29 20:47
 */
/*
    发明专利的 mapper 层接口
 */
@Mapper
public interface InventionPatentMapper extends BaseMapper<InventionPatent> {
}
