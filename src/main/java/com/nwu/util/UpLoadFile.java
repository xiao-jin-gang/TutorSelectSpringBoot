package com.nwu.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 上传文件/删除文件
 */
@Component
public class UpLoadFile {


    /**
     * @param uploadFile 上传的文件
     * @param req        http
     * @param typeName   上传文件所属名称  例：学术论文/文科
     * @param tutorId    教师工号
     * @return 文件存储路径
     */
    public String upload(MultipartFile uploadFile, HttpServletRequest req, String typeName, String tutorId) {
        String format = tutorId + "/" + typeName;
        File file = new File(DataUtils.FilePath + format);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        long time = new Date().getTime();
        String oldName = uploadFile.getOriginalFilename();
        String newName = oldName.substring(0, oldName.lastIndexOf(".")) + time + oldName.substring(oldName.lastIndexOf("."), oldName.length());

        try {
            uploadFile.transferTo(new File(file, newName));
            //String filePath= req.getScheme()+"://"+ req.getServerName() +":"+req.getServerPort()+"/uploadFile/"+format+"/"+oldName;
            // http://localhost:8081/upfile/uploadFile/20133220/%E5%AD%A6%E6%9C%AF%E8%AE%BA%E6%96%87/%E5%AF%BC%E5%B8%88%E9%81%B4%E9%80%89.rar
            String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/downFile/uploadFile/" + format + "/" + newName;
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
