package com.nwu.vo;

import lombok.Data;

/*
*  封装 修改审核状态时下发的对象
*/
@Data
public class UpdateStatus {
    private int id_1; //id
    private int status_1;     //修改后的状态
    private String commit_1;   //备注
}
