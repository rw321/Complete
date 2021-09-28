package com.example.complete.design.iterator;

import java.util.Iterator;

public class BookShelfIterator implements Iterator {

    private BookShelf mBookShelf;
    private int index;

    public BookShelfIterator(BookShelf mBookShelf) {
        this.mBookShelf = mBookShelf;
    }

    @Override
    public boolean hasNext() {
        return index < mBookShelf.size();
    }

    @Override
    public Object next() {
        Book book = mBookShelf.getIndexAt(index);
        index++;
        return book;
    }
}
