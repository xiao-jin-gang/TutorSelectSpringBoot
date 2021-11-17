package com.nwu.service.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.entities.ApplyType;

import java.util.List;

public interface ApplyTypeService extends IService<ApplyType> {
    List<ApplyType> getAll();
}
