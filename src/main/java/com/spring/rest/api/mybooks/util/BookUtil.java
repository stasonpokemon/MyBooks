package com.spring.rest.api.mybooks.util;

import com.spring.rest.api.mybooks.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookUtil {

    public static void copyNonNullBookValues(Book requestBook, Book dbBook) {
        if (requestBook.getAuthor() != null) {
            dbBook.setAuthor(requestBook.getAuthor());
        }
        if (requestBook.getName() != null) {
            dbBook.setName(requestBook.getName());
        }
        if (requestBook.getCode() != null) {
            dbBook.setCode(requestBook.getCode());
        }
        if (requestBook.getPublicationDate() != null) {
            dbBook.setPublicationDate(requestBook.getPublicationDate());
        }

    }
}
