package com.nwu.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author dynamic
 * @since 2021-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("system_user")
@ApiModel(value="SystemUser对象")
public class SystemUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("tutor_id")
    @ApiModelProperty(value = "学工号")
    private String tutorId;
    @ApiModelProperty(value = "用户姓名")
    private String name;
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    private int roleId; // 角色 id

    @ApiModelProperty(value = "用户状态 0 禁用；1启用")
    private Integer status;

    @ApiModelProperty(value = "系统用户创建时间")
    private String createTime;

    @ApiModelProperty(value = "系统用户院系名称")
    private int organizationId;

    private String organizationName;


}
