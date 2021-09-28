package com.example.complete.design.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookShelf implements Aggregate {


    private List<Book> books;

    public BookShelf() {
        books = new ArrayList<>();
    }


    public int size() {
        return books.size();
    }

    public Book getIndexAt(int index) {
        if (index < 0 || index >= books.size())
            return null;
        return books.get(index);
    }

    public void add(Book book) {
        books.add(book);
    }

    /**
     *
     * @return 返回Iterator 面向接口编程,不要面向过程编程
     */
    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
