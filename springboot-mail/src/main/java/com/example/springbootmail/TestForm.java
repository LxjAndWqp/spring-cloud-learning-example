package com.example.springbootmail;

import java.util.List;

public class TestForm {


    /**
     * userId : 1149
     * waybillCode : YDD201910280002
     * areaName : 上海科捷仓库2
     * receiverName : 是是非非
     * receiverPhone : 18767675656
     * receiveWarehouse : 中国移动有限公司
     * startAddress : 陝西省西安市高新区锦业一路120号
     * targetAddress : 广东省江门市新会区新会经济开发区碧华园3期
     * createdTime : 2019-10-30 15:58:11
     * totalMaterialAmount : 5500
     * receiverCode : 6337972220908079789
     * signTime : 2019-11-14 12:00 00
     * materialList : [{"materialDescribe":"oppo手机","materialCode":"oppo123","logisticsInstruction":"16709876253","materialAmount":500},{"materialDescribe":"华为手机","materialCode":"oppo123","logisticsInstruction":"16709876255","materialAmount":5000}]
     */

    private String userId;
    private String waybillCode;
    private String areaName;
    private String receiverName;
    private String receiverPhone;
    private String receiveWarehouse;
    private String startAddress;
    private String targetAddress;
    private String createdTime;
    private int totalMaterialAmount;
    private String receiverCode;
    private String signTime;
    private List<MaterialListBean> materialList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWaybillCode() {
        return waybillCode;
    }

    public void setWaybillCode(String waybillCode) {
        this.waybillCode = waybillCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiveWarehouse() {
        return receiveWarehouse;
    }

    public void setReceiveWarehouse(String receiveWarehouse) {
        this.receiveWarehouse = receiveWarehouse;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public int getTotalMaterialAmount() {
        return totalMaterialAmount;
    }

    public void setTotalMaterialAmount(int totalMaterialAmount) {
        this.totalMaterialAmount = totalMaterialAmount;
    }

    public String getReceiverCode() {
        return receiverCode;
    }

    public void setReceiverCode(String receiverCode) {
        this.receiverCode = receiverCode;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public List<MaterialListBean> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<MaterialListBean> materialList) {
        this.materialList = materialList;
    }

    public static class MaterialListBean {
        /**
         * materialDescribe : oppo手机
         * materialCode : oppo123
         * logisticsInstruction : 16709876253
         * materialAmount : 500
         */

        private String materialDescribe;
        private String materialCode;
        private String logisticsInstruction;
        private int materialAmount;

        public String getMaterialDescribe() {
            return materialDescribe;
        }

        public void setMaterialDescribe(String materialDescribe) {
            this.materialDescribe = materialDescribe;
        }

        public String getMaterialCode() {
            return materialCode;
        }

        public void setMaterialCode(String materialCode) {
            this.materialCode = materialCode;
        }

        public String getLogisticsInstruction() {
            return logisticsInstruction;
        }

        public void setLogisticsInstruction(String logisticsInstruction) {
            this.logisticsInstruction = logisticsInstruction;
        }

        public int getMaterialAmount() {
            return materialAmount;
        }

        public void setMaterialAmount(int materialAmount) {
            this.materialAmount = materialAmount;
        }
    }
}
