package com.lidong.econtract.controller;

import com.lidong.econtract.utils.HttpClientUtils;
import com.lidong.econtract.model.HttpResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/econtract")
public class EcontractController {


    /***
     *根据手机号查询认证信息接口
     *[说明] 根据手机号码查询实名认证表的记录，返回数据可以确认之前是否有认证过
     * @return
     */
    @PostMapping(value = "/auth/getAuthInfoByMobileNumber",produces = "application/json")
    public HttpResultModel getAuthInfoByMobileNumber(@RequestBody Map<String,Object>  params) {
        String mobileNumber = (String) params.get("mobileNumber");
        HttpResultModel resultModel = HttpClientUtils.getAuthInfoByMobileNumber(mobileNumber);
        return resultModel;
    }

    /**
     * 创建待签名记录接口
     * @param params
     * @return
     */
    @PostMapping(value = "/signature/addSignature",produces = "application/json")
    public HttpResultModel addSignature(@RequestBody Map<String,Object>  params) {
        Integer userId = (Integer) params.get("userId");
        Integer signatureId = (Integer) params.get("signatureId");
        HttpResultModel resultModel = HttpClientUtils.addSignature(userId,signatureId);
        return resultModel;
    }

    /**
     * 通过上传创建待签名记录
     * @param fileType
     * @param file
     * @param userId
     * @return
     */
    @PostMapping(value = "/signature/pdfFileUpload", produces = "application/json")
    public HttpResultModel pdfFileUpload(@RequestParam("userId") Integer userId,
                                         @RequestParam("file") MultipartFile file,
                                         @RequestParam("fileType") String fileType) {
        HttpResultModel resultModel = HttpClientUtils.pdfFileUpload(userId, file, fileType);
        return resultModel;
    }

    /**
     * 电子签章记录详情接口简化版本
     * @param params
     * @return
     */
    @PostMapping(value = "/signature/getSignatureDetailSimplify", produces = "application/json")
    public HttpResultModel getSignatureDetailSimplify(@RequestBody Map<String,Object>  params) {
        Integer signatureId = (Integer)params.get("signatureId");
        HttpResultModel resultModel = HttpClientUtils.getSignatureDetailSimplify(signatureId);
        return resultModel;
    }


}
