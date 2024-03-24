package com.teamthree.pdmapi.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.teamthree.pdmapi.model.Book;
import com.teamthree.pdmapi.model.Genre;
import com.teamthree.pdmapi.persistence.BookDAO;
import com.teamthree.pdmapi.persistence.BookDatabaseDAO;
import com.teamthree.pdmapi.persistence.ConnectionHandler;


@RestController
@RequestMapping("book")
public class BookController {

    private static final Logger LOG = Logger.getLogger(AccountController.class.getName());
    private BookDAO bookDAO;

    private BookController(ConnectionHandler ch) {
        bookDAO = new BookDatabaseDAO(ch);
    }

    @GetMapping("/getBookId/{bookId}")
    public ResponseEntity<Book> getBookId(@PathVariable("bookId") String bookId) {
        LOG.info("GET /book/getBookId/" + bookId);
        Book result = bookDAO.getBookId(bookId);
        return new ResponseEntity<Book>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @GetMapping("/getBookTitle/{bookTitle}")
    public ResponseEntity<Book> getBookTitle(@PathVariable("bookTitle") String bookTitle) {
        LOG.info("GET /book/getBookTitle/" + bookTitle);
        Book result = bookDAO.getBookId(bookTitle);
        return new ResponseEntity<Book>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Boolean> createBook(@RequestParam("bookId") String bookId, @RequestParam("bookTitle") String bookTitle) {
        LOG.info("POST /book/addBook?bookId=" + bookId + "&bookTitle=" + bookTitle);
        boolean result = bookDAO.createBook(bookId, bookTitle);
        return new ResponseEntity<Boolean>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping("/bookGenre/")
    public ResponseEntity<Boolean> setBookGenre(@RequestParam("bookId") String bookId, @RequestParam("genre") Genre genre) {
        LOG.info("POST /book/bookGenre?bookId=" + bookId + "&genre=" + genre);
        boolean result = bookDAO.setBookGenre(bookId, genre);
        return new ResponseEntity<Boolean>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @GetMapping("/bookGenre/{bookId}")
    public ResponseEntity<List<Genre>> getBookGenres(@RequestParam("bookId") String bookId) {
        LOG.info("GET /book/bookGenre/" + bookId);
        List<Genre> result = bookDAO.getBookGenres(bookId);
        return new ResponseEntity<List<Genre>>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
