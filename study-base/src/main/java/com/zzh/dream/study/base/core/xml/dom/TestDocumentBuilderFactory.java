package com.zzh.dream.study.base.core.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @description: javax.xml.parsers 包中的DocumentBuilderFactory用于创建DOM模式的解析器对象
 *      DocumentBui lderFactory是一个抽象工厂类，它不能直接实例化，但该类提供了一个newInstance方法 ，
 *      这个方法会根据本地平台默认安装的解析器，自动创建一个工厂的对象并返回。
 * 优点：
 * 允许应用程序对数据和结构做出更改
 * 访问是双向的，可以在任何时候再树中上、下导航获取、操作任意部分的数据
 *
 * 缺点：
 * 解析XML文档的需要加载整个文档来构造层次结构，消耗内存资源大
 *
 * 适用：遍历能力强，常应用于XML文档需要频繁改变的服务中
 *
 * @author: zhangzihao
 * @date: 03/11/2021
 **/
public class TestDocumentBuilderFactory {

    /**
     * 创建一个 DocumentBuilderFactory 对象
     * 创建一个 DocumentBuilder 对象
     * 通过 DocumentBuilder 的 parse() 方法加载 XML 到当前工程目录下
     * 通过 getElementsByTagName() 方法获取所有 XML 所有节点的集合
     * 遍历所有节点
     * 通过 item() 方法获取某个节点的属性
     * 通过 getNodeName() 和 getNodeValue() 方法获取属性名和属性值
     * 通过 getChildNodes() 方法获取子节点，并遍历所有子节点
     * 通过 getNodeName() 和 getTextContent() 方法获取子节点名称和子节点值
     * @param args
     */
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse("./study-base/src/main/java/com/zzh/dream/study/base/core/xml/BookXml.xml");
            NodeList bookList = document.getElementsByTagName("book");
            int bookCnt = bookList.getLength();
            System.err.println("一共获取到" + bookCnt +"本书");

            for(int i=0; i<bookCnt; i++)
            {
                Node book = bookList.item(i);
                NamedNodeMap attrs = book.getAttributes();
                for(int j=0; j<attrs.getLength(); j++){
                    Node attr = attrs.item(j);
                    System.err.println(attr.getNodeName()+"---"+attr.getNodeValue());//id

                }

                NodeList childNodes = book.getChildNodes();
                for(int k=0; k<childNodes.getLength(); k++){
                    if(childNodes.item(k).getNodeType() == Node.ELEMENT_NODE){
                        System.out.println(childNodes.item(k).getNodeName()+"---" + childNodes.item(k).getTextContent());
                    }
                }
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
