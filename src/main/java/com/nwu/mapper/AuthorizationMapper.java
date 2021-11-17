package com.nwu.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Rex Joush
 * @time 2021.09.08 16:13
 */
@Mapper
public interface AuthorizationMapper {

    String getAuthorization(String tutorId);

}
