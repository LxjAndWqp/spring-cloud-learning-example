package com.lidong.econtract.model;

import java.io.Serializable;

public class AddSignatureVO implements Serializable {
    Integer id;//	签署的id号
    String pdfUrl;//	PDF链接
    Integer userId;//	PDF链接

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
