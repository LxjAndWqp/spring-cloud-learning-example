package com.example.springbootmail;

import com.alibaba.fastjson.JSON;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.itextpdf.text.pdf.BaseFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @Author :qinpansheng@e6yun.com
 * @Data :2019/11/12 21:02
 * @description :
 */

public class ItextPdfUtils {

    private static final Logger log = LoggerFactory.getLogger(ItextPdfUtils.class);


    public static void main(String[] args) {
        String json = "{\n" +
                "    \"userId\": \"1149\",\n" +
                "    \"waybillCode\": \"YDD201910280002\",\n" +
                "    \"areaName\": \"上海科捷仓库2\",\n" +
                "    \"receiverName\":\"是是非非\",\n" +
                "    \"receiverPhone\":\"18767675656\",\n" +
                "    \"receiveWarehouse\": \"中国移动有限公司\",\n" +
                "    \"startAddress\":\"陝西省西安市高新区锦业一路120号\",\n" +
                "    \"targetAddress\": \"广东省江门市新会区新会经济开发区碧华园3期\",\n" +
                "    \"createdTime\": \"2019-10-30 15:58:11\",\n" +
                "    \"totalMaterialAmount\": 5500,\n" +
                "    \"receiverCode\":\"6337972220908079789\",\n" +
                "    \"signTime\":\"2019-11-14 12:00 00\",\n" +
                "    \"materialList\": [\n" +
                "        {\n" +
                "            \"materialDescribe\": \"oppo手机\",\n" +
                "            \"materialCode\": \"oppo123\",\n" +
                "            \"logisticsInstruction\": \"16709876253\",\n" +
                "            \"materialAmount\": 500\n" +
                "        },\n" +
                "        {\n" +
                "            \"materialDescribe\": \"华为手机\",\n" +
                "            \"materialCode\": \"oppo123\",\n" +
                "             \"logisticsInstruction\": \"16709876255\",\n" +
                "            \"materialAmount\": 5000\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        TestForm parse = JSON.parseObject(json, TestForm.class);
        List<OrderPdfVO> orderList = new ArrayList<>();

        List<TestForm.MaterialListBean> sonList = parse.getMaterialList();
        Integer countMaterialAmount = 0;
        if (sonList != null && sonList.size() > 0) {
            for (int i = 0; i < sonList.size(); i++) {
                boolean add = orderList.add(new OrderPdfVO(sonList.get(i).getMaterialCode(),
                        sonList.get(i).getMaterialDescribe(),
                        sonList.get(i).getMaterialAmount() + "", "KKKKKKKKKKKKKKKKKKKK",
                        sonList.get(i).getLogisticsInstruction()));
                countMaterialAmount += sonList.get(i).getMaterialAmount();
            }
        }
        //物料总计
        parse.setTotalMaterialAmount(countMaterialAmount);
        //设置pdf文件名为运单编号
        String pdfName = parse.getWaybillCode() + ".pdf";
        pdfout(parse,orderList,pdfName);
    }

    // 利用模板生成pdf
    public static File pdfout(TestForm orderWaybillVO,List<OrderPdfVO> orderPdfVOList,String pdfFileName) {
        // 模板路径
        String templatePath = "D:\\zhongyi\\app\\testout.pdf";
        // 生成的新文件路径
        String newPDFPath = "D:\\zhongyi\\app\\pdf\\"+pdfFileName;
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            //给表单添加中文字体 这里采用系统字体。不设置的话，中文可能无法显示
            BaseFont bf = BaseFont.createFont("c:\\Windows\\Fonts\\simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font FontChinese = new Font(bf, 5, Font.NORMAL);
            // 输出流
            out = new FileOutputStream(newPDFPath);
            // 读取pdf模板
            reader = new PdfReader(templatePath);
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            Map<String,Object> map= packageMap(orderWaybillVO);
            //图片类的内容处理
            Map<String,String> imgmap = (Map<String,String>)map.get("imgmap");
            for(String key : imgmap.keySet()) {
                String value = imgmap.get(key);
                String imgpath = value;
                List<AcroFields.FieldPosition> list = form.getFieldPositions(key);
                Rectangle signRect = list.get(0).position;
                float x = signRect.getLeft();
                float y = signRect.getBottom();
                //根据路径读取图片
                Image image = Image.getInstance(imgpath);
                //获取图片页面
                PdfContentByte under = stamper.getOverContent(1);
                //图片大小自适应
                image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                //添加图片
                image.setAbsolutePosition(x, y);
                under.addImage(image);
            }
            //遍历条码字段
            Map<String,Object> barcodeFields=(Map<String, Object>) map.get("barcodeFields");
            for (Map.Entry<String, Object> entry : barcodeFields.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                // 获取属性的类型
                if(value != null && form.getField(key) != null){
                    //获取位置(左上右下)
                    FieldPosition fieldPosition = form.getFieldPositions(key).get(0);
                    //绘制条码
                    Barcode128 barcode128 = new Barcode128();
                    //字号
                    barcode128.setSize(10);
                    //条码高度
                    barcode128.setBarHeight(35);
                    //条码与数字间距
                    barcode128.setBaseline(10);
                    //条码值
                    barcode128.setCode(value.toString());
                    barcode128.setStartStopText(false);
                    barcode128.setExtended(true);
                    //绘制在第一页
                    PdfContentByte cb = stamper.getOverContent(1);
                    //生成条码图片
                    Image image128 = barcode128.createImageWithBarcode(cb, null, null);
                    //左边距(居中处理)
                    float marginLeft = (fieldPosition.position.getRight() - fieldPosition.position.getLeft() - image128.getWidth()) / 2;
                    //条码位置
                    image128.setAbsolutePosition(fieldPosition.position.getLeft() + marginLeft, fieldPosition.position.getBottom());
                    //加入条码
                    cb.addImage(image128);
                }
            }
            //遍历map数据
            Map<String,String> datemap = (Map<String,String>)map.get("datemap");
            // 添加所创建的字体
            form.addSubstitutionFont(bf);
            for(String key : datemap.keySet()){
                String value = datemap.get(key);
                form.setField(key,value);
            }
            // 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
            List<AcroFields.FieldPosition> list = form.getFieldPositions("table");
            Rectangle rect = list.get(0).position;
            PdfPTable table= packageTable(rect,orderPdfVOList, 400, 10.0F);
            float x = rect.getLeft();
            float y = rect.getBottom();
            //获取图片页面
            PdfContentByte under = stamper.getOverContent(1);
            /*//添加table
            table.setAbsolutePosition(x, y);
            under.addImage(image);*/
            table.writeSelectedRows(0, -1, rect.getLeft(), rect.getTop(), under);
            stamper.setFormFlattening(true);
            stamper.close();
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
            File file = new File(newPDFPath);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }


    ////组装pdf表头的单个域内容
    public static Map packageMap(TestForm orderWaybillVO) {
        //组装pdf表头的单个域内容
        //条形码map
        Map<String, Object> barcodeFields = new HashMap<String, Object>();
        barcodeFields.put("waybillCode", orderWaybillVO.getWaybillCode());
        //图片
        Map<String, Object> imgmap = new HashMap<String, Object>();
        imgmap.put("Imageurl", "D:\\zhongyi\\app\\yd.png");
        //文本内容map
        Map<String, String> map = new HashMap<String, String>();
        map.put("startAddress", orderWaybillVO.getStartAddress());
        map.put("receiveWarehouse", orderWaybillVO.getReceiveWarehouse());
        map.put("receiverName", orderWaybillVO.getReceiverName());
        map.put("receiverPhone", orderWaybillVO.getReceiverPhone());
        map.put("targetAddress", orderWaybillVO.getTargetAddress());
        map.put("receiverCode", orderWaybillVO.getReceiverCode());
       // map.put("signTime", orderWaybillVO.getSignTime().toString());
        map.put("countMaterialAmount", orderWaybillVO.getTotalMaterialAmount()+"");
        //组装orderWaybillMap传过去
        Map<String, Object> orderWaybillMap = new HashMap<String, Object>();
        orderWaybillMap.put("barcodeFields", barcodeFields);
        orderWaybillMap.put("datemap", map);
        orderWaybillMap.put("imgmap", imgmap);
        return orderWaybillMap;
    }

    //组装pdf 中的table

    public static PdfPTable packageTable(Rectangle rect,List<OrderPdfVO> list, Integer tableWidth, Float fontSize) {
        PdfWriter writer = null;
        FileOutputStream out = null;
        Document document = null;
        try {
            // 创建table,注意这里的5是四列的意思,下面通过table.addCell添加的时候必须添加整行内容的所有列
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(0);
            BaseFont bfChinese =  BaseFont.createFont("c:\\Windows\\Fonts\\simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontZH = new Font(bfChinese, fontSize == null ? 12F : fontSize, 0);
            //第一行是列表名
            table.setHeaderRows(1);
            table.getDefaultCell().setHorizontalAlignment(1);
            float tatalWidth = rect.getRight() - rect.getLeft() - 1;
           //计算表格宽度
           float[] columnWidth ={(float) (tatalWidth*0.2),(float)(tatalWidth*0.2),(float)(tatalWidth*0.2),(float)(tatalWidth*0.2),(float)(tatalWidth*0.2)};
           table.setTotalWidth(columnWidth);
           table.setLockedWidth(true);
            //遍历List 获取每一列的数据，填充到pdfTable中
            list.forEach(orderPdfVO -> {
                table.addCell(createCell(orderPdfVO.getCol1(), fontZH, 1));
                table.addCell(createCell(orderPdfVO.getCol2(), fontZH, 1));
                table.addCell(createCell(orderPdfVO.getCol3(), fontZH, 1));
                table.addCell(createCell(orderPdfVO.getCol4(), fontZH, 1));
                table.addCell(createCell(orderPdfVO.getCol5(), fontZH, 1));
            });
            return table;
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }


    private static PdfPCell createCell(String value, Font font, int align) {
        PdfPCell cell = new PdfPCell();
         cell.setVerticalAlignment(Element.ALIGN_CENTER);
         cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value, font));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }
}
