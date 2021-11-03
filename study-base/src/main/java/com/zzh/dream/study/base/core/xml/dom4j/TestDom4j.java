package com.zzh.dream.study.base.core.xml.dom4j;

import com.zzh.dream.study.base.core.xml.sax.Book;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description: （Document Object Model for Java）解析
 * 优点：
 * 性能很好
 * 大量使用 Java 集合类，开发简便，同时也提供了一些提高性能的代替方法
 * 支持 XPath
 *
 * 缺点：
 * API 比较复杂
 *
 * @author: zhangzihao
 * @date: 03/11/2021
 **/
public class TestDom4j {

    /**
     * 创建 SAXReader 的对象 reader
     * 通过 reader 对象的 read() 方法加载 books.xml 文件，获取 document 对象
     * 通过 document 对象获取根节点 bookstore
     * 通过 element 对象的 elementIterator() 获取迭代器
     * 遍历迭代器，获取根节点中的信息
     * 获取 book 的属性名和属性值
     * 通过 book 对象的 elementIterator() 获取节点元素迭代器
     * 遍历迭代器，获取子节点中的信息
     * 获取节点名和节点值
     * @param args
     */
    public static void main(String[] args) {

        ArrayList<Book> bookList = new ArrayList<Book>();
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new File("./study-base/src/main/java/com/zzh/dream/study/base/core/xml/BookXml.xml"));
            Element bookStore = document.getRootElement();
            Iterator it = bookStore.elementIterator();
            while (it.hasNext()) {

                Element book = (Element) it.next();
                Book bookData = new Book();
                List<Attribute> bookAttrs = book.attributes();
                for (Attribute attr : bookAttrs) {
                    System.out.println(attr.getName() + "---" + attr.getValue());

                    if(attr.getName().equals("id")){
                        bookData.setId(attr.getValue());
                    }
                }
                Iterator itt = book.elementIterator();

                while (itt.hasNext()) {
                    Element bookChild = (Element) itt.next();

                    System.out.println(bookChild.getName()+ "---" + bookChild.getText());

                    if(bookChild.getName().equals("name")){
                        bookData.setName(bookChild.getText());
                    }else if(bookChild.getName().equals("author")){
                        bookData.setAuthor(bookChild.getText());
                    }else if(bookChild.getName().equals("year")){
                        bookData.setYear(bookChild.getText());
                    }else if(bookChild.getName().equals("price")){
                        bookData.setPrice(bookChild.getText());
                    }else if(bookChild.getName().equals("language")){
                        bookData.setLanguage(bookChild.getText());
                    }
                }
                // 遍历完一个节点，将该节点信息添加到列表中
                bookList.add(bookData);

            }
        } catch (DocumentException e) {

            e.printStackTrace();
        }

        // 输出保存在内存中XML信息
        for(Book book:bookList){
            System.out.println(book.getName());
            System.out.println("id=" + book.getId());
            System.out.println(book.getAuthor());
            System.out.println(book.getYear());
            System.out.println(book.getPrice());
            System.out.println(book.getLanguage());
        }


    }
}
