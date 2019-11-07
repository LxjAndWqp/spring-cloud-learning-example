package com.lidong.econtract.model;

import java.io.Serializable;

public class UserAuth implements Serializable {
    Integer id;//	认证标识	是
    String userName;//	用户名	是
    String mobileNumber	;//用户手机号	是
    String idNumber;//	用户身份证	是
    String createTime;//	时间	是
    Integer status	;// 状态 1通过，2不通过，3未认证

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserAuth{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status=" + status +
                '}';
    }
}
