package com.nwu.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 所有管理员展示页的申请信息表，中间状态，获取此结果后，继续查询所有信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyInfo implements Serializable {

    private int applyId;    // 申请 id
    private int applyTypeId; // 申请类别 id

}
