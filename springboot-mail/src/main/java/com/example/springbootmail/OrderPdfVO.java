package com.example.springbootmail;

import com.alibaba.fastjson.JSON;

/**
 * 订单的pdfVO 用于生成pdf
 * pdf格式参考以下 pdf模板分为四列填充
 * 运单编号：	201908808
 * 发货仓库名称：	北京分仓
 * 客户名称：	陕西省丈八一路
 * 收货地址：	陕西省丈八一路
 * 运单创建时间：	2019-09-23：12:00
 * 物料总数：	300
 *
 * 行项	物料描述	物料编号	数量
 * 1	iphone1	M9797998	2
 * 2	iphone1	M9797998	23
 * 3	iphone1	M9797998	23
 *
 * @version 1.0
 * @Author :qinpansheng@e6yun.com
 * @Data :2019/10/23 17:09
 * @description :
 */
public class OrderPdfVO {
    private String col1;
    private String col2;
    private String col3;
    private String col4;
    private String col5;
    public OrderPdfVO() {
    }
    public OrderPdfVO(String col1, String col2, String col3, String col4,String col5) {
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;
        this.col4 = col4;
        this.col4 = col5;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public String getCol4() {
        return col4;
    }

    public void setCol4(String col4) {
        this.col4 = col4;
    }

    public String getCol5() {return col5;}

    public void setCol5(String col5) {this.col5 = col5;}

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
