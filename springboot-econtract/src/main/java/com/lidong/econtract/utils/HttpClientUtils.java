package com.lidong.econtract.utils;

import com.alibaba.fastjson.JSON;
import com.lidong.econtract.model.HttpResultModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class HttpClientUtils {

    public final static String BASE_URL = "http://120.196.212.10";

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL 例如：http://localhost:8080/demo/login
     * @param param 请求参数 例：{ "userName":"admin", "password":"123456" }
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendPostJSON(String url, Map<String, Object> param) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(JSON.toJSONString(param), "UTF-8");
        stringEntity.setContentType("text/json");
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = httpclient.execute(httpPost);
        HttpEntity entity = null;
        try {
            entity = response.getEntity();
            return EntityUtils.toString(entity);
        } finally {
            EntityUtils.consume(entity);
            response.close();
        }
    }


    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    public static File multipartFileToFile(MultipartFile file) throws Exception {
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());

            inputStreamToFile(ins, toFile);

            ins.close();

        }

        return toFile;

    }


    /**
     * @param url
     * @param file
     * @param params
     * @return
     * @throws Exception
     */
    public static String sendPostUploadFile(String url,
                                            MultipartFile file,
                                            Map<String, String> params) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建 HttpPost
        HttpPost httpPost = new HttpPost(url);
        //创建上传文件的表单
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.addPart("file", new FileBody(multipartFileToFile(file)));//添加上传的文件
        setUploadParams(entityBuilder, params);
        HttpEntity httpEntity = entityBuilder.build();
        httpPost.setEntity(httpEntity);
        //执行post 请求
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = null;
        try {
            entity = response.getEntity();
            return EntityUtils.toString(entity);
        } finally {
            EntityUtils.consume(entity);
            response.close();
        }
    }

    /**
     * 设置上传文件时所附带的其他参数
     *
     * @param multipartEntityBuilder
     * @param params
     */
    private static void setUploadParams(MultipartEntityBuilder multipartEntityBuilder, Map<String, String> params) {
        if (params != null && params.size() > 0) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                multipartEntityBuilder
                        .addPart(key, new StringBody(params.get(key),
                                ContentType.TEXT_PLAIN));
            }
        }
    }

    /**
     *
     */
    public static HttpResultModel getAuthInfoByMobileNumber(String mobileNumber) {
        String url = BASE_URL + "/econtract/auth/getAuthInfoByMobileNumber";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mobileNumber", mobileNumber);
//        param.put("mobileNumber", "13261570828");
        try {
            String s = sendPostJSON(url, param);
            System.out.println(s);
            HttpResultModel httpResultModel = JSON.parseObject(s, HttpResultModel.class);
            return httpResultModel;
        } catch (Exception e) {
            log.error("getAuthInfoByMobileNumber",e);
        }
        return null;
    }

    public static void main(String[] args) {
//        send();
    }

    /**
     * 2.2.1创建待签名记录接口
     *
     * @param userId
     * @param signatureId
     * @return
     */
    public static HttpResultModel addSignature(Integer userId, Integer signatureId) {
        String url = BASE_URL + "/econtract/signature/addSignature";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("signatureId", signatureId);
        try {
            String s = sendPostJSON(url, param);
            System.out.println(s);
            HttpResultModel httpResultModel = JSON.parseObject(s, HttpResultModel.class);
            return httpResultModel;
        } catch (Exception e) {
            log.error("addSignature",e);
    }
        return null;
    }

    /**
     * 2.2.1创建待签名记录接口
     *
     * @param userId
     * @param file
     * @param fileType
     * @return
     */
    public static HttpResultModel pdfFileUpload(Integer userId, MultipartFile file, String fileType) {
        String url = BASE_URL + "/econtract/signature/pdfFileUpload";
        Map<String, String> param = new HashMap<String, String>();
        param.put("userId", userId + "");
        param.put("fileType", fileType);
        try {
            String s = sendPostUploadFile(url, file, param);
            System.out.println(s);
            HttpResultModel httpResultModel = JSON.parseObject(s, HttpResultModel.class);
            return httpResultModel;
        } catch (Exception e) {
            log.error("pdfFileUpload",e);
        }
        return null;
    }

    public static HttpResultModel getSignatureDetailSimplify(Integer signatureId) {
        String url = BASE_URL + "/econtract/signature/getSignatureDetailSimplify";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("signatureId", signatureId);
        try {
            String s = sendPostJSON(url, param);
           log.debug(s);
            HttpResultModel httpResultModel = JSON.parseObject(s, HttpResultModel.class);
            return httpResultModel;
        } catch (Exception e) {
            log.error("getSignatureDetailSimplify",e);
        }
        return null;
    }
}
