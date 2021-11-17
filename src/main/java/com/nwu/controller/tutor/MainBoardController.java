package com.nwu.controller.tutor;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nwu.entities.Apply;
import com.nwu.entities.tutor.FirstPage;
import com.nwu.results.Result;
import com.nwu.results.ResultCode;
import com.nwu.service.TutorInspectService;
import com.nwu.service.admin.ApplyService;
import com.nwu.service.tutor.PageInit;
import com.nwu.service.tutor.common.DeleteFileService;
import com.nwu.service.tutor.common.MainBoardService;
import com.nwu.service.tutor.common.TeacherInfoService;
import com.nwu.service.tutor.noInspectApply.NoFirstService;
import com.nwu.util.IdUtils;
import com.nwu.util.UpLoadFile;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rex Joush
 * @time 2021.08.24 20:48
 */
@RestController
public class MainBoardController {

    // 申请表
    @Resource
    private MainBoardService mainBoardService;

    // 教师基本信息
    @Resource
    private TeacherInfoService teacherInfoService;

    @Resource
    private TutorInspectService tutorInspectService;

    // 免审第一页
    @Resource
    private NoFirstService noFirstService;

    // 删除文件
    @Resource
    private DeleteFileService deleteFileService;

    @Resource
    private ApplyService applyService;

    /**
     * 修复第一次申请刷新页面的 bug
     * @param applyTypeId 申请 id
     * @param request 请求对象
     */
    @GetMapping("/tutor/getApplyId/{applyTypeId}")
    public Result getApplyId(@PathVariable("applyTypeId") int applyTypeId,
                             HttpServletRequest request) {
        String tutorId = IdUtils.getTutorId(request);

        QueryWrapper<Apply> wrapper = new QueryWrapper<>();
        wrapper.eq("tutor_id", tutorId);
        wrapper.eq("status", 0);
        wrapper.eq("apply_type_id", applyTypeId);

        Apply apply = applyService.getOne(wrapper);
        if (apply == null) {
            return new Result(ResultCode.SUCCESS, -1);
        } else {
            return new Result(ResultCode.SUCCESS, apply.getApplyId());
        }
    }

    /**
     * 判断申请状态
     * @param applyTypeId 申请类别Id
     * @return data
     */
    @ApiOperation("是否申请过此岗位")
    @GetMapping("/tutor/checkApply/{applyTypeId}")
    public Result checkApply(@PathVariable("applyTypeId") int applyTypeId,
                             HttpServletRequest request) {

        String tutorId = IdUtils.getTutorId(request);

        /*
            根据 tutorId 和 applyId 查询申请信息
            101：已经申请过此岗位，但信息未填写完成
            100：已经申请过此岗位，且信息已提交完成
            102：未申请过此岗位
         */
        List<Apply> applyList = mainBoardService.getApplyByTutorIdAndApplyTypeId(tutorId, applyTypeId);
        HashMap<String, Object> map = new HashMap<>();

        boolean flag = true;

        // 没申请过
        if (applyList.size() == 0) {
            map.put("applyId", -1);
            map.put("applyCondition", 102);
        } else {
            switch (applyTypeId) {
                // 只允许有一个
                case 1:
                case 4:
                case 7:
                    // 查看申请状态
                    if (applyList.get(0).getStatus() == 0) {
                        map.put("applyId", applyList.get(0).getApplyId());
                        map.put("applyCondition", 101);
                    } else {
                        map.put("applyCondition", 100);
                    }
                    break;
                // 允许有多个
                case 2:
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                    for (Apply apply : applyList) {
                        // 查到有在申请中的申请，返回当前申请
                        if (apply.getStatus() == 0) {
                            flag = false;
                            map.put("applyId", apply.getApplyId());
                            map.put("applyCondition", 101);
                            break;
                        }
                    }
                    // 所有申请均已提交，返回申请过此类岗位
                    if (flag) {
                        map.put("applyId", -1);
                        map.put("applyCondition", 100);
                    }
                    break;
            }
        }

        return new Result(ResultCode.SUCCESS, map);
    }


    /**
     * 导师免审
     *
     * @param applyTypeId 申请类型 id
     * @param applyCondition 申请状态
     * @param applyId 申请 id
     */
    @GetMapping("/tutor/getNoFirstPage/{applyTypeId}/{applyCondition}/{applyId}")
    public Result getNoFirstPage(@PathVariable("applyTypeId") int applyTypeId,
                                 @PathVariable("applyCondition") Integer applyCondition,
                                 @PathVariable("applyId") Integer applyId,
                                 HttpServletRequest request) {

        String tutorId = IdUtils.getTutorId(request);

        FirstPage firstPage;
        FirstPage noFirstPage;
        try {
            if (applyCondition == 102) {
                // 未申请过 查找teacherInfo
                firstPage = teacherInfoService.getTeacherInfo(tutorId);
                return new Result(ResultCode.SUCCESS, firstPage);
            } else if (applyCondition == 101) {
                // 已申请过 查询对应的主键 导师增列
                if (applyTypeId == 3 || applyTypeId == 6 || applyTypeId == 9) {
                    // 导师免审 查询 tutor_no_inspect
                    noFirstPage = noFirstService.getNoFirstPage(applyId);
                    return new Result(ResultCode.SUCCESS, noFirstPage);
                } else {
                    // 查询 tutorInspect
                    firstPage = tutorInspectService.getFirstPage(applyId);
                    return new Result(ResultCode.SUCCESS, firstPage);
                }
            } else {
                return Result.FAIL();
            }
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 1201);
            jsonObject.put("message", "您不在此系统中，请联系系统管理员");
            jsonObject.put("errorMessage", e.getMessage());
            return new Result(ResultCode.SUCCESS, jsonObject);
        }
    }

    /**
     * 文件上传controller
     *
     * @param uploadFile
     * @param typeId     上传文件类别 学术论文/科研奖励等  11=博士首次申请+学术论文 41=硕士首次申请+学术论文
     * @param request
     * @return path路径
     */
    @ApiOperation("文件上传")
    @PostMapping("/user/upload/{typeId}")
    public Result uploadFile(@RequestParam("material") MultipartFile uploadFile,
                             @PathVariable("typeId") Integer typeId,
                             HttpServletRequest request) {

        String tutorId = IdUtils.getTutorId(request);

        UpLoadFile loadFile = new UpLoadFile();
        String typeName = "";
        if (!"".equals(typeId)) {
            switch (typeId) {
                case 1: {
                    typeName = typeName + "学术论文/社科类论文";
                    break;
                }
                case 2: {
                    typeName = typeName + "学术论文/理工类论文";
                    break;
                }
                case 3: {
                    typeName = typeName + "科研项目";
                    break;
                }
                case 4: {
                    typeName = typeName + "教材或学术著作";
                    break;
                }
                case 5: {
                    typeName = typeName + "科研教学奖励";
                    break;
                }
                case 6: {
                    typeName = typeName + "发明专利";
                    break;
                }
                case 7: {
                    typeName = typeName + "免审资料";
                    break;
                }
                default: {
                    return Result.FAIL();
                }
            }
            String path = loadFile.upload(uploadFile, request, typeName, tutorId);
            if (!"".equals(path)) {
                //路径不为空
                HashMap<String, Object> map = new HashMap<>();
                map.put("fileType", typeId);
                map.put("path", path);
                return new Result(ResultCode.SUCCESS, map);
            } else {   //路径为空
                return Result.FAIL();
            }
        }
        return Result.FAIL();
    }

    /**
     * 文件删除
     *
     * @param httpPath
     * @return Result.SUCCESS();  .FAIL()
     * @throws UnsupportedEncodingException
     */
    @ApiOperation("文件删除")
    @PostMapping("/user/delFile")
    public Result delFile(@RequestBody String httpPath ,HttpServletRequest request) throws UnsupportedEncodingException {
        try {
            deleteFileService.delFile(httpPath,request);
        }
        catch (Exception e){
            return new Result(ResultCode.SUCCESS, PageInit.getErrorMessage(e)); //1201
        }
        return new Result(ResultCode.SUCCESS, Map.of("code",1200));

    }
    @ApiOperation("文件删除")
    @PostMapping("/user/noDelFile/{applyId}")
    public Result noDelFile(@PathVariable("applyId") String applyId, @RequestBody String httpPath ,HttpServletRequest request) throws UnsupportedEncodingException {
        try {
            deleteFileService.noDelFile(httpPath, request, applyId);
        }
        catch (Exception e){
            return new Result(ResultCode.SUCCESS, PageInit.getErrorMessage(e)); //1201
        }
        return new Result(ResultCode.SUCCESS, Map.of("code",1200));

    }
}
