package com.lidong.econtract.model;

import java.io.Serializable;

public class SignatureDetailSimplify implements Serializable {

    Integer id;//	签署文件id	是
    String userId;//	用户id号	是
    Float posX;//	坐标X	否
    Float posY;//	坐标Y	否
    String fileType;//	文件类型	是
    String mobileNumber	;//用户手机号	是
    String signatureReason;//	签署原因	否	String
    String  sealId;//	印章id	否	String
    String certificateId;//	证书id	否	String
    String account;//	用户名	是	String
    String signatureTime;//	签名时间	否	String
    String pdfUrl;//	pdf地址	是	String
    String documentHash	;//文档hash值	是	String
    String signatureStatus;//	签署状态	是

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Float getPosX() {
        return posX;
    }

    public void setPosX(Float posX) {
        this.posX = posX;
    }

    public Float getPosY() {
        return posY;
    }

    public void setPosY(Float posY) {
        this.posY = posY;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSignatureReason() {
        return signatureReason;
    }

    public void setSignatureReason(String signatureReason) {
        this.signatureReason = signatureReason;
    }

    public String getSealId() {
        return sealId;
    }

    public void setSealId(String sealId) {
        this.sealId = sealId;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSignatureTime() {
        return signatureTime;
    }

    public void setSignatureTime(String signatureTime) {
        this.signatureTime = signatureTime;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getDocumentHash() {
        return documentHash;
    }

    public void setDocumentHash(String documentHash) {
        this.documentHash = documentHash;
    }

    public String getSignatureStatus() {
        return signatureStatus;
    }

    public void setSignatureStatus(String signatureStatus) {
        this.signatureStatus = signatureStatus;
    }
}
