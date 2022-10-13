package com.spring.rest.api.mybooks.controller;

import com.spring.rest.api.mybooks.entity.Book;
import com.spring.rest.api.mybooks.exception.BookNotFoundException;
import com.spring.rest.api.mybooks.service.BookService;
import com.spring.rest.api.mybooks.util.BookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookRestController {


    @Autowired
    private BookService bookService;

    /**
     * Takes Book Object as input and returns save Status as ResponseEntity<String>
     */
    @PostMapping()
    public ResponseEntity<String> saveBook(@RequestBody Book book) {
        ResponseEntity<String> response;
        try {
            Long id = bookService.saveBook(book);
            response = new ResponseEntity<String>("Book '" + id + "' created", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<String>("Unable to save book", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    /**
     * Takes Book Objects as input and returns save Status as ResponseEntity<?>
     */
    @PostMapping("/add_some")
    public ResponseEntity<?> saveSomeBooks(@RequestBody List<Book> books) {
        ResponseEntity<?> response;
        try {
            List<Book> savedBooks = bookService.saveAllBook(books);
            response = new ResponseEntity<List<Book>>(savedBooks, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<String>("Unable to save books", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    /**
     * To read all Books, returns data retrieval Status as ResponseEntity<?>
     */
    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        ResponseEntity<?> response;
        try {
            List<Book> books = bookService.getAllBooks();
            response = new ResponseEntity<List<Book>>(books, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<String>("Unable to get Books",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    /**
     * To read one Books by providing id, returns Books object & Status as ResponseEntity<?>
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneBook(@PathVariable("id") Long id) {
        ResponseEntity<?> response;
        try {
            Book book = bookService.getOneBook(id);
            response = new ResponseEntity<Book>(book, HttpStatus.OK);
        } catch (BookNotFoundException bookNotFoundException) {
            throw bookNotFoundException; // re-throw exception to handler
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<String>("Unable to find Book",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    /**
     * To delete one Book by providing id, returns Status as ResponseEntity<String>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        ResponseEntity<String> response;

        try {
            bookService.deleteBook(id);
            response = new ResponseEntity<String>("Book '" + id + "' deleted", HttpStatus.OK);
        } catch (BookNotFoundException bookNotFoundException) {
            throw bookNotFoundException; // re-throw exception to handler
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<String>(
                    "Unable to delete Book", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    /**
     * To delete some Books by providing List, returns Status as ResponseEntity<String>
     */
    @DeleteMapping("/delete_some")
    public ResponseEntity<?> deleteSomeBooks(@RequestBody List<Book> books) {
        ResponseEntity<?> response;
        try {
            bookService.deleteAll(books);
            response = new ResponseEntity<List<Book>>(books, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<String>(
                    "Unable to delete Book", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }


    /**
     * To modify one Book by providing id, updates Invoice object & returns Status as ResponseEntity<String>
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable("id") Long id,
                                             @RequestBody Book book) {
        ResponseEntity<String> response;

        try {
            //db Object
            Book bookFromDb = bookService.getOneBook(id);
            //copy non-null values from request to Database object
            BookUtil.copyNonNullBookValues(book, bookFromDb);
            //finally update this object
            bookService.updateBook(bookFromDb);
            response = new ResponseEntity<String>("Book '" + id + "' Updated",
                    HttpStatus.RESET_CONTENT); //205- Reset-Content(PUT)
        } catch (BookNotFoundException bookNotFoundException) {
            throw bookNotFoundException; // re-throw exception to handler
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<String>("Unable to Update Book", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    /**
     * To update one Book just like where clause condition, updates Book object & returns Status as ResponseEntity<String>
     */
    @PatchMapping("/{id}/{code}")
    public ResponseEntity<String> updateBookCodeById(@PathVariable("id") Long id,
                                                     @PathVariable("code") String code) {
        ResponseEntity<String> response;

        try {
            bookService.updateBookCodeById(code, id);
            response = new ResponseEntity<String>(
                    "Book '" + code + "' Updated",
                    HttpStatus.PARTIAL_CONTENT); //206- Reset-Content(PUT)
        } catch (BookNotFoundException bookNotFoundException) {
            throw bookNotFoundException; // re-throw exception to handler
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseEntity<String>("Book to Update Invoice", HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return response;
    }
}
