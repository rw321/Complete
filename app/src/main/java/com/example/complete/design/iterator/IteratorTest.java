package com.example.complete.design.iterator;

import java.util.Iterator;

/**
 * 迭代器模式: 主要是将迭代和具体实现分开,不因聚合对象的存储方式改变而影响迭代
 */
public class IteratorTest {

    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf();
        bookShelf.add(new Book("斗破苍穹"));
        bookShelf.add(new Book("虎道人"));
        bookShelf.add(new Book("盘龙"));
        Iterator iterator = bookShelf.iterator();
        while (iterator.hasNext()) {
            Book next = (Book) iterator.next();
            System.out.println("book name is " + next.name);
        }
    }

}
