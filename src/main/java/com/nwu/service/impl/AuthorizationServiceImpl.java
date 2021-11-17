package com.nwu.service.impl;

import com.nwu.mapper.AuthorizationMapper;
import com.nwu.service.AuthorizationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Rex Joush
 * @time 2021.09.08 16:11
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Resource
    private AuthorizationMapper authorizationMapper;

    @Override
    public String getAuthorization(String tutorId) {

        return authorizationMapper.getAuthorization(tutorId);

    }
}
