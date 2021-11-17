package com.nwu.entities;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Apply对象")
@TableName("apply")
public class Apply implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "apply_id", type = IdType.AUTO)
    private int applyId; // 申请表id 自增
    private String tutorId;  // 导师工号
    private String name;     // 导师姓名
    private int organizationId; // 导师所在院系 id
    private int applyTypeId;   // 申请的类别 id
    private int status;  // 当前申请的状态
    private int subject;  // 分科，文科或理科
    private int professional;  // 专硕或学硕
    private String commit;  // 院系秘书初审的备注
    private  String commitYxSfh; //院系秘书提交分会页面的备注
    private String commitYxXy; //院系秘书录入分会意见页面的备注
    private String commitYjsyCs;  // 研究生院管理员初审的备注
    private  String commitYjsySfh; //研究生院管理员提交分会页面的备注
    private String commitYjsyLr; //研究生院管理员录入分会意见页面的备注
    private String commitSocial;//社科处的备注
    private String submitTime; // 提交申请的时间

}
