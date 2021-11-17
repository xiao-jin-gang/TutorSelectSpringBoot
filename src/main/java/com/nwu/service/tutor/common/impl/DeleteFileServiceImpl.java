package com.nwu.service.tutor.common.impl;

import com.nwu.mapper.TutorNoInspectMapper;
import com.nwu.service.tutor.common.DeleteFileService;
import com.nwu.util.DataUtils;
import com.nwu.util.UpLoadFile;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Service
public class DeleteFileServiceImpl implements DeleteFileService{

    @Resource
    UpLoadFile upLoadFile;

    @Resource
    TutorNoInspectMapper tutorNoInspectMapper;

    /**
     * 删除文件
     * @param httpPath http://10.8.47.148:8081/downFile/uploadFile/20133220/学术论文/社科类论文/导师遴选.rar
     * @return ok err
     */
    @Override
    public void delFile(String httpPath,HttpServletRequest request) {

        if (!"".equals(httpPath)) {
            try {
                String path = URLDecoder.decode(httpPath, "UTF-8");
                //处理字符串
                String res =  request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/downFile/uploadFile/";
                String realPath= DataUtils.FilePath + path.substring(res.length(), path.length() - 1);
                File file = new File(realPath);
                if (file.exists()){
                    file.delete();
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("删除文件错误！"+"!"+e.getMessage());
            }
        }
    }

    @Override
    public void noDelFile(String httpPath, HttpServletRequest request, String applyId) {
        if (!"".equals(httpPath)) {
            try {
                String path = URLDecoder.decode(httpPath, "UTF-8");
                //处理字符串
                String res =  request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/downFile/uploadFile/";
                String realPath= DataUtils.FilePath + path.substring(res.length(), path.length() - 1);
                File file = new File(realPath);
                if (file.exists()){
                    file.delete();
                }
                //数据库删除
                tutorNoInspectMapper.updateTutorNoInspectFilePath(applyId,"");
            } catch (Exception e) {
                throw new RuntimeException("删除文件错误！"+"!"+e.getMessage());
            }
        }
    }
}
