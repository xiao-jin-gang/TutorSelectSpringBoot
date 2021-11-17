package com.nwu.service.tutor.common;

import javax.servlet.http.HttpServletRequest;

public interface DeleteFileService {
    /**
     * 非免审文件删除
     * @param httpPath
     * @param request
     */
    public void delFile(String httpPath,HttpServletRequest request);

    /**
     * 免审文件删除
     * @param httpPath
     * @param request
     * @param applyId
     */
    void noDelFile(String httpPath,HttpServletRequest request,String applyId);
}
