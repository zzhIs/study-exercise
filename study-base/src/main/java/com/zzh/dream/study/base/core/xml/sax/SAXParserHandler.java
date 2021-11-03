package com.zzh.dream.study.base.core.xml.sax;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserHandler extends DefaultHandler {
    String value = null;
    Book book = null;
    private ArrayList<Book> bookList = new ArrayList<Book>();

    public ArrayList<Book> getBookList() {
        return bookList;
    }
    /*
     * XML 解析开始
     */
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("xml 解析开始");
    }

    /*
     * XML 解析结束
     */
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("xml 解析结束");
    }

    /*
     * 解析 XML 元素开始
     */
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        super.startElement(uri, localName, qName, attributes);

        if(qName.equals("book")){
            book = new Book();

            for(int i=0; i<attributes.getLength();i++){
                System.out.println(attributes.getQName(i)+"---"+attributes.getValue(i));
                if(attributes.getQName(i).equals("id")){
                    book.setId(attributes.getValue(i));
                }
            }
        }else if(!qName.equals("bookstore")){
            System.out.print("节点名："+ qName + "---");
        }
    }

    /*
     *解析 XML 元素结束
     */
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        super.endElement(uri, localName, qName);
        if(qName.equals("book")){
            bookList.add(book);
            book = null;
        }
        else if(qName.equals("name")){
            book.setName(value);
        }else if(qName.equals("year")){
            book.setYear(value);
        }else if(qName.equals("author")){
            book.setAuthor(value);
        }else if(qName.equals("price")){
            book.setPrice(value);
        }else if(qName.equals("language")){
            book.setLanguage(value);
        }
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);

        // 获取节点值数组
        value = new String(ch, start, length);
        if(!value.trim().equals("")){
            System.out.println("节点值："+value);
        }
    }
}
