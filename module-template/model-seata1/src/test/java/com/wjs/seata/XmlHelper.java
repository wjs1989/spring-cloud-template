package com.wjs.seata;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class XmlHelper {

    public static void main(String[] args) {
        // 解析books.xml文件
        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        try {
            // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
            Document document = reader.read(new File("D:\\ppp.xml"));
            // 通过document对象获取根节点bookstore
            Element tbody = document.getRootElement();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator tbodyIt = tbody.elementIterator();
            // 遍历迭代器，获取根节点中的信息（书籍）
            while (tbodyIt.hasNext()) {
                Element tr = (Element) tbodyIt.next();
                // 获取book的属性名以及 属性值
//                List<Attribute> trAttrs = tr.attributes();
//                for (Attribute attr : bookAttrs) {
//                    System.out.println("属性名：" + attr.getName() + "--属性值："
//                            + attr.getValue());
//                }
                Iterator trIt = tr.elementIterator();
                String[] value = new String[9];
                int index = 0;
                while (trIt.hasNext()) {
                    Element td = (Element) trIt.next();
                    //   System.out.println("节点名：" + bookChild.getName() + "--节点值：" + bookChild.getStringValue());
                    if (index <= 1) {
                        value[index++] = td.getStringValue();
                    } else if (index > 1) {
                        Iterator tdIt = td.elementIterator();
                        while (tdIt.hasNext()) {
                            Element span = (Element) tdIt.next();
                            Iterator spanIt = span.elementIterator();
                            while (spanIt.hasNext()) {
                                Element i = (Element) spanIt.next();
                                value[index++] = i.getStringValue();
                            }
                        }
                    } else {
                        break;
                    }
                }
                String sql = String.format("INSERT INTO `lottery`(`red1`, `red2`, `red3`, `red4`, `red5`, `blue1`, `blue2`, `release_time`, `num`) VALUES (  %s, %s, %s, %s, %s, %s, %s, '%s', '%s');",
                        value[2],value[3],value[4],value[5],value[6],value[7],value[8],value[1],value[0]);

                System.out.println(sql);

            }
        } catch (Exception e) {
        }
    }
}
