package com.nwu.vo;

import lombok.Data;

/**
 * 论文查询参数
 */

@Data
public class PaperQuery {
    private String tutorId;
    private String paperName;//论文名称
    private String firstAuthorName;//第一作者
    private String paperPublicationTime;//发表时间
    private String journalName;//期刊名称
    private String journalLevel;//期刊等级
    private String paperProveMaterials;//证明材料
    private String col1;//成果认定（通过、驳回）
    private String col2;//备注
}
