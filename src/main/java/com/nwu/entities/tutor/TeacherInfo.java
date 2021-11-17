package com.nwu.entities.tutor;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Blob;

/**
 * @author Rex Joush
 * @time 2021.08.23 15:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherInfo implements Serializable {

    @TableField("XM")
    private String XM;      // 姓名

    @TableField("SFZJH")
    private String SFZJH;   // 身份证号

    @TableField("XB")
    private String XB;      // 性别

    @TableField("SJH")
    private String SJH;     // 手机号

    @TableField("MC")
    private String MC;      // 单位

    @TableField("SHZ")
    private byte[] SHZ;     // 照片信息

    @TableField("CSRQ")
    private String CSRQ;    // 出生日期

    @TableField("ZGXW")
    private String ZGXW;    // 最高学位

    @TableField("ZGH")
    private String ZGH;     // 工号

    @TableField("ZCMC")
    private String ZCMC;    // 职称

    @TableField("DM")
    private String DM;      //学院代码

}
