package com.nwu.entities.tutor.childSubject;

/**
 * @author Rex Joush
 * @time 2021.09.01 09:45
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 前端第三页删除的信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteItem {

    private int deleteId;       // 删除的id
    private String deletePath;  // 删除的文件路经
    private int deleteType;     // 删除的类型

}
