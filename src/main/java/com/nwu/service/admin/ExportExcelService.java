package com.nwu.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.entities.TutorInspect;
import com.nwu.entities.tutor.FirstPage;
import com.nwu.vo.QueryDepartmentSecretaryInit;
import com.nwu.vo.TutorQuery;

import java.util.List;

public interface ExportExcelService extends IService<QueryDepartmentSecretaryInit> {

     List<QueryDepartmentSecretaryInit> getTutorByQuery(TutorQuery tutorQuery);
}
