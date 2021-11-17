package com.nwu.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("apply_type")
@ApiModel(value="ApplyType对象", description="")
public class ApplyType implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "apply_type_id", type = IdType.ID_WORKER)
    private int applyTypeId;  //申请类型id

    private String applyName;  //申请类型名称

    private String applyPath;  //申请路径
}
