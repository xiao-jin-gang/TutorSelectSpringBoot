package com.nwu.mapper;

import com.nwu.entities.SystemUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nwu.vo.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author dynamic
 * @since 2021-08-10
 */
@Mapper
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    List<SystemUser> getAll(@Param("query") UserQuery query, int pageStart);

    List<String> getQueryTutorId(String tutorId);

    SystemUser getSystemUserByTutorId(String tutorId);
}
