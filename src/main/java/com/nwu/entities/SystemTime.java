package com.nwu.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@ApiModel(value="SystemTime对象", description="")
public class SystemTime implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "time_id",type = IdType.AUTO)
    private Integer timeId;

    private Integer organizationId;

    private String startTime;

    private String endTime;

    private String col1;


}
