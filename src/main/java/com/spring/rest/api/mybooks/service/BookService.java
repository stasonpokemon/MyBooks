package com.spring.rest.api.mybooks.service;

import com.spring.rest.api.mybooks.entity.Book;

import java.util.List;

public interface BookService {

    /**
     * Takes Book Object as input and returns PK generated
     */
    Long saveBook(Book book);

    /**
     * Takes existing Book data as input and updates values
     */
    void updateBook(Book book);

    /**
     * Takes PK(ID) as input and deletes Book Object data
     */
    void deleteBook(Long id);

    /**
     * Takes id as input and returns one row as one object
     */
    Book getOneBook(Long id);  //used in RestController

    /**
     * select all rows and provides result as a List<Book>
     */
    List<Book> getAllBooks();

    /**
     * Takes Id as input,checks if record exists returns true, else false
     *
     */
    boolean isBookExist(Long id);

    /**
     * Takes 2 fields as input, updates Book data as provided where clause
     * like 'UPDATE Book SET code=:code WHERE id=:id'
     */
    Integer updateBookCodeById(String code,Long id);
}
