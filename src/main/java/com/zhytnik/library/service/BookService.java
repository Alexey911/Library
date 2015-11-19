package com.zhytnik.library.service;

import com.zhytnik.library.model.Book;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.lang.String.format;
import static java.util.Objects.isNull;

public class BookService extends Service<Book> {
    public BookService() {
        super();
    }

    @Override
    public void validateFullness(Book b) {
        String illegalFormat = "Set %s of book: %s";
        if (isNullOrEmpty(b.getName())) {
            throwIllegalArgException(format(illegalFormat, "name", b));
        }
        if (b.getPageCount() <= 0) {
            throwIllegalArgException(format(illegalFormat, "page count", b));
        }
        if (isNullOrEmpty(b.getAuthors())) {
            throwIllegalArgException(format(illegalFormat, "authors", b));
        }
        if (isNull(b.getPublisher())) {
            throwIllegalArgException(format(illegalFormat, "publisher", b));
        }
        String format = "%s length must be less than or equals to %d";
        if (b.getName().length() > 50) {
            throwIllegalArgException(format(format, "Name", 50));
        }
        if (b.getAuthors().length() > 100) {
            throwIllegalArgException(format(format, "Authors", 100));
        }
    }
}