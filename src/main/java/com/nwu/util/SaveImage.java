package com.nwu.util;


import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

@Component
public class SaveImage {

    /**
     * 保存数据库图片到本地
     *
     * @param blobImage byte[]
     * @param fileName  导师工号
     * @param request   httpRequest
     * @return path
     */
    public static String ExportBlob(byte[] blobImage, String fileName, HttpServletRequest request) throws Exception {
        if (blobImage == null) {
            return "";
        }

        String path = DataUtils.imagePath + fileName + ".jpg";

        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            for (int i = 0; i < blobImage.length; i++) {
                // 调整异常数据
                if (blobImage[i] < 0) {
                    blobImage[i] += 256;
                }
            }
            FileOutputStream outputStream = new FileOutputStream(path);
            outputStream.write(blobImage);
            outputStream.flush(); // 刷新
            outputStream.close(); // 关闭字节输出流
            return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/downFile/image/" + fileName + ".jpg";
        } catch (Exception e) {
            return "";
        }
    }
}
