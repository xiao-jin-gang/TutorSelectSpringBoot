package com.nwu.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nwu.entities.tutor.TeacherInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

/**
 * @author Rex Joush
 * @time 2021.08.23 14:51
 */
public class ResultClient {

    /**
     * 根据导师工号返回基本信息
     * @param tutorId 教师工号
     * @return 教师信息
     */
    public TeacherInfo getDataInfo(String tutorId) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            String url = "http://111.114.175.13:8300/getrest/dslxjzgxx";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("page", 1);
            jsonObject.put("pagesize", 10);

            // paramString 可参考 API 说明，paramString 传参为 JSON 格式的 String 字符串，且需用实力进制 toHexString 转换后传递
            JSONObject paramString = new JSONObject();
            paramString.put("ZGH", tutorId);
            jsonObject.put("paramString", toHexString(paramString.toString()));

            StringEntity entity = new StringEntity(jsonObject.toString(), "UTF-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            HttpPost method = new HttpPost(url);
            method.setEntity(entity);

            // 密钥传递 applyId、secretKey
            // method.setHeader("serviceNum", "******");
            method.setHeader("applyId", "20134297_1588211078248");
            method.setHeader("secretKey", "c9fe5738-d035-41ef-83a4-76daeb051be3");
            HttpResponse result = httpClient.execute(method);
            HttpEntity ret = result.getEntity();
            // return EntityUtils.toString(ret);
            String dataInfo = EntityUtils.toString(ret);
            Object data = JSON.parseObject(dataInfo).get("data");
            JSONArray roWs = JSON.parseArray(JSON.parseObject(data.toString()).get("Rows").toString());
            return roWs.getObject(0, TeacherInfo.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toHexString(String s) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            str.append(Integer.toHexString(ch));
        }
        return str.toString();
    }
}

