package com.zzh.dream.study.base.core.xml.sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * @description: （SAXSimple API for XML）解析
 * 优点：
 * 不需要等待所有的数据被处理，解析就可以开始
 * 只在读取数据时检查数据，不需要保存在内存中
 * 可以在某一个条件满足时停止解析，不必要解析整个文档
 * 效率和性能较高，能解析大于系统内存的文档
 *
 * 缺点：
 * 解析逻辑复杂，需要应用层自己负责逻辑处理，文档越复杂程序越复杂
 * 单向导航，无法定位文档层次，很难同时同时访问同一文档的不同部分数据，不支持 XPath
 *
 * @author: zhangzihao
 * @date: 03/11/2021
 **/
public class TestSax {

    public static void main(String[] args) {
        parseXml();
    }

    /**
     * 获取一个 SAXParserFactory 的实例
     * 通过 factory() 获取 SAXParser 实例
     * 创建一个 handler() 对象
     * 通过 parser 的 parse() 方法来解析 XML
     */
    private static void parseXml(){
// 获取实例
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            SAXParserHandler handler = new SAXParserHandler();
            parser.parse("./study-base/src/main/java/com/zzh/dream/study/base/core/xml/BookXml.xml", handler);

            System.err.println("共有"+ handler.getBookList().size()+ "本书");
            for(Book book : handler.getBookList()){
                System.out.println(book.getName());
                System.out.println("id=" + book.getId());
                System.out.println(book.getAuthor());
                System.out.println(book.getYear());
                System.out.println(book.getPrice());
                System.out.println(book.getLanguage());
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }
}
