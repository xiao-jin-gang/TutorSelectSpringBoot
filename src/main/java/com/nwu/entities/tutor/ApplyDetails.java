package com.nwu.entities.tutor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Rex Joush
 * @time 2021.09.13 19:44
 */

/*
    我的申请详情页
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyDetails implements Serializable {

    // 非免审
    private FirstPage firstPage;
    private SecondPage secondPage;
    private ThirdPage thirdPage;
    private FourthPage fourthPage;

    // 免审
    private FirstPage noFirstPage;
    private NoSecondPage noSecondPage;

}
