package com.nwu.entities.tutor.childSubject;

/**
 * @author Rex Joush
 * @time 2021.08.24 16:08
 */

import lombok.Data;

/**
 * 何时参加何种学术团体、任何种职务，有何社会兼职列表
 */
@Data
public class GroupsOrPartTimeJob {

    private String time;    // 参加时间
    private String groups;  // 参加团体
    private String job;     // 所任职务

}
