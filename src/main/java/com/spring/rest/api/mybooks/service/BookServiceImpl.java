package com.spring.rest.api.mybooks.service;

import com.spring.rest.api.mybooks.entity.Book;
import com.spring.rest.api.mybooks.exception.BookNotFoundException;
import com.spring.rest.api.mybooks.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;


    @Override
    public Long saveBook(Book book) {
        return bookRepository.save(book).getId();
    }

    @Override
    public List<Book> saveAllBook(List<Book> books) {
        return bookRepository.saveAll(books);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = getOneBook(id);
        bookRepository.delete(book);
    }

    @Override
    public void deleteAll(Iterable<? extends Book> entities) {
        bookRepository.deleteAll(entities);
    }

    @Override
    public Book getOneBook(Long id) {
        return bookRepository.findById(id).
                orElseThrow(() -> new BookNotFoundException(
                        new StringBuilder()
                                .append("Book '")
                                .append(id)
                                .append("' isn't exist").toString()));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public boolean isBookExist(Long id) {
        return bookRepository.existsById(id);
    }

    @Override
    @Transactional
    public Integer updateBookCodeById(String code, Long id) {
        if (!isBookExist(id)){
            throw new BookNotFoundException(new StringBuilder()
                    .append("Book '")
                    .append(id)
                    .append("' isn't exist").toString());
        }
        return bookRepository.updateBookCodeById(code, id);
    }
}
