package com.nwu.mapper.tutor.common;

import com.nwu.entities.Apply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface TutorApplyMapper {
    /**
     * 查询教师工号查询 tutor_id 是否存在
     * @param tutorId 教师 id
     * @return 受影响的行数
     */
    int getApplyByTutorId(@Param("tutorId") String tutorId);

    /**
     * 根据导师工号和申请类别查询出对应的apply信息
     *
     * @param tutorId 导师工号
     * @param applyTypeId 申请类别
     * @return apply 信息
     */
    List<Apply> getApplyByTutorIdAndApplyTypeId(@Param("tutorId") String tutorId, @Param("applyTypeId") Integer applyTypeId);

    /**
     * 根据导师工号和申请类别查询出对应的apply信息
     * @param tutorId 教师工号
     * @param applyTypeId 申请类别
     * @return 申请信息
     */
    Apply getApplyByTutorIdAndApplyTypeIdAndStatus(@Param("tutorId") String tutorId, @Param("applyTypeId") Integer applyTypeId);

    /**
     * 根据导师工号和申请类别状态码查询出对应的apply信息
     * @param tutorId
     * @param applyTypeId
     * @return
     */
    List<Apply> getApplyListByTutorIdAndApplyTypeId(@Param("tutorId") String tutorId,@Param("applyTypeId") Integer applyTypeId);
    /**
     * 添加申请表信息 tutor_id apply_type_id status
     * @param apply 申请信息
     * @return 影响的行数
     */
    int saveApplyInfo(@Param("apply") Apply apply);

    /**
     * 添加申请别的学科申请类别
     * @param applyId 申请表 id
     * @param applySubject 学科类型，1文史，2理工，3交叉学科
     * @return 修改结果
     */
    int updateApplySubject(int applyId, int applySubject);

    /**
     * 根据工号和申请类别，申请状态查询对应的主键id
     * @param tutorId 教师工号
     * @param applyTypeId 申请类型
     * @param status 申请状态
     * @return 申请的 id
     */
    int getApplyId(String tutorId, int applyTypeId, int status);


    /**
     * 根据教师工号和申请状态查询主键 id
     * @param tutorId 教师工号
     * @param status 申请表状态
     * @return 申请表 id
     */
    int getApplyIdByTutorIdAndStatus(@Param("tutorId") String tutorId, @Param("status") Integer status);
}
