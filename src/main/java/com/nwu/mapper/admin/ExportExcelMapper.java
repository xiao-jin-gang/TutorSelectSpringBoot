package com.nwu.mapper.admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nwu.vo.QueryDepartmentSecretaryInit;
import com.nwu.vo.TutorQuery;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ExportExcelMapper extends BaseMapper<QueryDepartmentSecretaryInit> {

    List<QueryDepartmentSecretaryInit> selectByQuery(TutorQuery tutorQuery);
}
