package com.teamthree.pdmapi.controller;

import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamthree.pdmapi.model.Audience;
import com.teamthree.pdmapi.model.Book;
import com.teamthree.pdmapi.model.BookContributor;
import com.teamthree.pdmapi.model.BookFormat;
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

    @GetMapping("/id/{bookId}")
    public ResponseEntity<Book> getBookId(@PathVariable("bookId") String bookId) {
        LOG.info("GET /book/id/" + bookId);
        Book result = bookDAO.getBookId(bookId);
        return new ResponseEntity<Book>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @GetMapping("/title/{bookTitle}")
    public ResponseEntity<Book> getBookTitle(@PathVariable("bookTitle") String bookTitle) {
        LOG.info("GET /book/title/" + bookTitle);
        Book result = bookDAO.getBook(bookTitle);
        return new ResponseEntity<Book>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Boolean> createBook(@RequestParam("bookTitle") String bookTitle) {
        LOG.info("POST /book/addBook?bookTitle=" + bookTitle);
        boolean result = bookDAO.createBook(bookTitle);
        System.out.println(result);
        return new ResponseEntity<Boolean>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping("/bookGenre")
    public ResponseEntity<Boolean> setBookGenre(@RequestParam("bookId") String bookId, @RequestParam("genreId") String genreId) {
        LOG.info("POST /book/bookGenre?bookId=" + bookId + "&genreId=" + genreId);
        boolean result = bookDAO.setBookGenre(bookId, genreId);
        return new ResponseEntity<Boolean>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @GetMapping("/bookGenres/{bookId}")
    public ResponseEntity<List<Genre>> getBookGenres(@PathVariable("bookId") String bookId) {
        LOG.info("GET /book/bookGenres/" + bookId);
        List<Genre> result = bookDAO.getBookGenres(bookId);
        return new ResponseEntity<List<Genre>>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping("/bookContributor")
    public ResponseEntity<Boolean> setBookContributor(@RequestParam("bookId") String bookId, @RequestParam("contributorId") String contributorId, @RequestParam("type") String type) {
        LOG.info("POST /book/bookContributor?bookId=" + bookId + "&contributorId=" + contributorId + "&type=" + type);
        boolean result = bookDAO.setBookContributor(bookId, contributorId, type);
        return new ResponseEntity<Boolean>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @GetMapping("/bookContributor/{bookId}")
    public ResponseEntity<List<BookContributor>> getBookContributors(@PathVariable("bookId") String bookId) {
        LOG.info("GET /book/bookContributor/" + bookId);
        List<BookContributor> result = bookDAO.getBookContributors(bookId);
        return new ResponseEntity<List<BookContributor>>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping("/bookFormat")
    public ResponseEntity<Boolean> setBookFormat(@RequestParam("bookId") String bookId, @RequestParam("formatId") String formatId, @RequestParam("length_pages") int length_pages, @RequestParam("release_date") Date release_date) {
        LOG.info("POST /book/bookContributor?bookId=" + bookId + "&formatId=" + formatId + "&length_pages=" + length_pages + "&release_date=" + release_date);
        boolean result = bookDAO.setBookFormat(bookId, formatId, length_pages, release_date);
        return new ResponseEntity<Boolean>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @GetMapping("/bookFormat/{bookId}")
    public ResponseEntity<List<BookFormat>> getBookFormats(@PathVariable("bookId") String bookId) {
        LOG.info("GET /book/bookFormat/" + bookId);
        List<BookFormat> result = bookDAO.getBookFormats(bookId);
        return new ResponseEntity<List<BookFormat>>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping("/bookAudience")
    public ResponseEntity<Boolean> setBookAudience(@RequestParam("bookId") String bookId, @RequestParam("audienceId") String audienceId) {
        LOG.info("POST /book/bookGenre?bookId=" + bookId + "&audienceId=" + audienceId);
        boolean result = bookDAO.setBookAudience(bookId, audienceId);
        return new ResponseEntity<Boolean>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @GetMapping("/bookAudience/{bookId}")
    public ResponseEntity<List<Audience>> getBookAudiences(@PathVariable("bookId") String bookId) {
        LOG.info("GET /book/bookAudience/" + bookId);
        List<Audience> result = bookDAO.getBookAudiences(bookId);
        return new ResponseEntity<List<Audience>>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @PostMapping("/rate")
    public ResponseEntity<Boolean> rateBook(@RequestParam("accountId") String accountId, @RequestParam("bookId") String bookId, @RequestParam("rating") Float rating) {
        LOG.info("POST /book/bookGenre?accountId=" + accountId + "&bookId=" + bookId + "&rating=" + rating);
        boolean result = bookDAO.rateBook(accountId, bookId, rating);
        return new ResponseEntity<Boolean>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @GetMapping("/rating/{bookId}")
    public ResponseEntity<Float> getBookRatingAvg(@PathVariable("bookId") String bookId) {
        LOG.info("GET /book/rating/" + bookId);
        Float result = bookDAO.getBookRatingAvg(bookId);
        return new ResponseEntity<Float>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @GetMapping("/rating")
    public ResponseEntity<Float> getAccountBookRating(@RequestParam("bookId") String bookId, @RequestParam("accountId") String accountId) {
        LOG.info("GET /book/rating?bookId=" + bookId + "&accountId=" + accountId);
        Float result = bookDAO.getAccountBookRating(bookId, accountId);
        return new ResponseEntity<Float>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
