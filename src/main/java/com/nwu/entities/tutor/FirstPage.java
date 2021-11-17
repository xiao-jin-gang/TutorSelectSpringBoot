package com.nwu.entities.tutor;

/**
 * @author Rex Joush
 * @time 2021.08.24 11:11
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加表格的第一页基本信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirstPage {

    @TableId(value = "apply_id", type = IdType.ID_WORKER)
    private String applyId;

    // 工号
    private String tutorId;

    // 姓名
    private String name;

    // 性别
    private String gender;

    // 图片 路径
    private String image;

    // blob图片
    private byte[] blobImage;

    // 所在单位id
    private Integer organizationId;

    // 所在单位名称
    private String organizationName;

    // 出生年月
    private String birthday;

    // 证件号码
    private String identity;

    // 联系电话
    private String phone;

    // 电子邮箱
    private String email;

    // 职称
    private String title;

    // 评定时间
    private String evaluateTime;

    // 最后学位
    private String finalDegree;

    // 授予单位
    private String awardDepartment;

    // 授予时间
    private String awardTime;

    // 授予单位及时间
    private String awardingUnitTime;


}
