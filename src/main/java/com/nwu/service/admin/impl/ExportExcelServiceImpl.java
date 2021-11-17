package com.nwu.service.admin.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.mapper.admin.ExportExcelMapper;
import com.nwu.service.admin.ExportExcelService;
import com.nwu.vo.QueryDepartmentSecretaryInit;
import com.nwu.vo.TutorQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExportExcelServiceImpl extends ServiceImpl<ExportExcelMapper, QueryDepartmentSecretaryInit> implements ExportExcelService {

    @Resource
    private ExportExcelMapper exportExcelMapper;

    @Override
    public List<QueryDepartmentSecretaryInit> getTutorByQuery(TutorQuery tutorQuery) {
        List<String> exportStatus = new ArrayList<>();
//        if(tutorQuery!=null&&tutorQuery.getApplyStatus()!=null) {
//            String[] split = tutorQuery.getApplyStatus().split("-");
//            for (String s : split) {
//                exportStatus.add(s);
//            }
//        }
//         tutorQuery.setApplyStatuss(exportStatus);
        return exportExcelMapper.selectByQuery(tutorQuery);
    }

}
