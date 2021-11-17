package com.nwu.vo;

import lombok.Data;

@Data
public class UpdatePaper {
    private int paperId;
    private String col1;//修改后的状态
    private String col2;//审核完带回的备注
}
