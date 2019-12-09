package com.lidong.flink.other;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;


public class XMLUtil {


    public static String convertToXml(Object obj) {
        StringWriter sw = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);//输出格式 是否为单行和格式化显示
            marshaller.marshal(obj, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        String replace = sw.toString().replace("standalone=\"yes\"", "");
        replace = replace.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>","");
        return replace;
    }

    public static void main(String[] args) {
        TextMessage account =new TextMessage();
        account.setContent("dddddddddddddd");
        account.setToUserName("dsdfsfsf");
        account.setCreateTime(System.currentTimeMillis());
        account.setMsgType("text");
        account.setFromUserName("gh_dddddddddddddddddddddd");
        String strXml= XMLUtil.convertToXml(account);
        System.out.println(strXml);
    }
}
