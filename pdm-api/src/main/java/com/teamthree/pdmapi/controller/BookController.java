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

/**
 * A controller for the BookDatabaseDAO
 * 
 * @author Caiden Williams
 */
@RestController
@RequestMapping("book")
public class BookController {

    private static final Logger LOG = Logger.getLogger(BookController.class.getName());
    private BookDAO bookDAO;

    /**
     * Handles REST API requests for book resorces
     * @param ch a connection handler to the database
     */
    private BookController(ConnectionHandler ch) {
        bookDAO = new BookDatabaseDAO(ch);
    }

    /**
     * Gets a book from the given Id
     * @param bookId the id of the book
     * @return the book with the matching Id (if there is one)
     */
    @GetMapping("/id/{bookId}")
    public ResponseEntity<Book> getBookId(@PathVariable("bookId") String bookId) {
        LOG.info("GET /book/id/" + bookId);
        Book result = bookDAO.getBookId(bookId);
        return new ResponseEntity<Book>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Gets a list of books with the given title
     * @param bookTitle the title of the book
     * @return the list books with the given title
     */
    @GetMapping("/title/{bookTitle}")
    public ResponseEntity<List<Book>> getBookTitle(@PathVariable("bookTitle") String bookTitle) {
        LOG.info("GET /book/title/" + bookTitle);
        List<Book> result = bookDAO.getBook(bookTitle);
        return new ResponseEntity<List<Book>>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Creates a book from the given title
     * @param bookTitle the book title
     * @return whether or not the book was successfully created
     */
    @PostMapping("/addBook")
    public ResponseEntity<Boolean> createBook(@RequestParam("bookTitle") String bookTitle) {
        LOG.info("POST /book/addBook?bookTitle=" + bookTitle);
        boolean result = bookDAO.createBook(bookTitle);
        System.out.println(result);
        return new ResponseEntity<Boolean>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Sets the genre of a book
     * @param bookId the id of the book
     * @param genreId the if of the genre
     * @return whether or not the book was successfully created
     */
    @PostMapping("/bookGenre")
    public ResponseEntity<Boolean> setBookGenre(@RequestParam("bookId") String bookId, @RequestParam("genreId") String genreId) {
        LOG.info("POST /book/bookGenre?bookId=" + bookId + "&genreId=" + genreId);
        boolean result = bookDAO.setBookGenre(bookId, genreId);
        return new ResponseEntity<Boolean>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Gets a list of genres from the given bookId
     * @param bookId the id of the book
     * @return a list of genres
     */
    @GetMapping("/bookGenres/{bookId}")
    public ResponseEntity<List<Genre>> getBookGenres(@PathVariable("bookId") String bookId) {
        LOG.info("GET /book/bookGenres/" + bookId);
        List<Genre> result = bookDAO.getBookGenres(bookId);
        return new ResponseEntity<List<Genre>>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Sets a books contributor and what they did
     * @param bookId the book id
     * @param contributorId the contributor id
     * @param type how the contributor contributed
     * @return whether or not the book was successfully created
     */
    @PostMapping("/bookContributor")
    public ResponseEntity<Boolean> setBookContributor(@RequestParam("bookId") String bookId, @RequestParam("contributorId") String contributorId, @RequestParam("type") String type) {
        LOG.info("POST /book/bookContributor?bookId=" + bookId + "&contributorId=" + contributorId + "&type=" + type);
        boolean result = bookDAO.setBookContributor(bookId, contributorId, type);
        return new ResponseEntity<Boolean>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Gets a list of contributors from the given bookId
     * @param bookId the id of the book
     * @return a list of contributors
     */
    @GetMapping("/bookContributor/{bookId}")
    public ResponseEntity<List<BookContributor>> getBookContributors(@PathVariable("bookId") String bookId) {
        LOG.info("GET /book/bookContributor/" + bookId);
        List<BookContributor> result = bookDAO.getBookContributors(bookId);
        return new ResponseEntity<List<BookContributor>>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Sets a books format, how long it is, and its release date
     * @param bookId the id of the book
     * @param formatId the id of the format
     * @param length_pages the length of the book
     * @param release_date the release date of the book
     * @return whether or not the book was successfully created
     */
    @PostMapping("/bookFormat")
    public ResponseEntity<Boolean> setBookFormat(@RequestParam("bookId") String bookId, @RequestParam("formatId") String formatId, @RequestParam("length_pages") int length_pages, @RequestParam("release_date") Date release_date) {
        LOG.info("POST /book/bookContributor?bookId=" + bookId + "&formatId=" + formatId + "&length_pages=" + length_pages + "&release_date=" + release_date);
        boolean result = bookDAO.setBookFormat(bookId, formatId, length_pages, release_date);
        return new ResponseEntity<Boolean>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Gets a list of formats from the given bookId
     * @param bookId the id of the book
     * @return a list of formats
     */
    @GetMapping("/bookFormat/{bookId}")
    public ResponseEntity<List<BookFormat>> getBookFormats(@PathVariable("bookId") String bookId) {
        LOG.info("GET /book/bookFormat/" + bookId);
        List<BookFormat> result = bookDAO.getBookFormats(bookId);
        return new ResponseEntity<List<BookFormat>>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Sets a books audience
     * @param bookId the id of the book
     * @param audienceId the id of the audience
     * @return whether or not the book was successfully created
     */
    @PostMapping("/bookAudience")
    public ResponseEntity<Boolean> setBookAudience(@RequestParam("bookId") String bookId, @RequestParam("audienceId") String audienceId) {
        LOG.info("POST /book/bookGenre?bookId=" + bookId + "&audienceId=" + audienceId);
        boolean result = bookDAO.setBookAudience(bookId, audienceId);
        return new ResponseEntity<Boolean>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Gets a list of audiences from the given bookId
     * @param bookId the id of the book
     * @return a list of audiences
     */
    @GetMapping("/bookAudience/{bookId}")
    public ResponseEntity<List<Audience>> getBookAudiences(@PathVariable("bookId") String bookId) {
        LOG.info("GET /book/bookAudience/" + bookId);
        List<Audience> result = bookDAO.getBookAudiences(bookId);
        return new ResponseEntity<List<Audience>>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Rats a book from the given account
     * @param accountId the id of the account
     * @param bookId the id of the book
     * @param rating the rating
     * @return whether or not the book was successfully rated
     */
    @PostMapping("/rate")
    public ResponseEntity<Boolean> rateBook(@RequestParam("accountId") String accountId, @RequestParam("bookId") String bookId, @RequestParam("rating") Float rating) {
        LOG.info("POST /book/bookGenre?accountId=" + accountId + "&bookId=" + bookId + "&rating=" + rating);
        boolean result = bookDAO.rateBook(accountId, bookId, rating);
        return new ResponseEntity<Boolean>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Gets the average rating of a boook
     * @param bookId the id of the book
     * @return the average rating
     */
    @GetMapping("/rating/{bookId}")
    public ResponseEntity<Float> getBookRatingAvg(@PathVariable("bookId") String bookId) {
        LOG.info("GET /book/rating/" + bookId);
        Float result = bookDAO.getBookRatingAvg(bookId);
        return new ResponseEntity<Float>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Gets an accounts rating of a book
     * @param bookId the id of the book
     * @param accountId the id of the account
     * @return the accounts rating of the book
     */
    @GetMapping("/rating")
    public ResponseEntity<Float> getAccountBookRating(@RequestParam("bookId") String bookId, @RequestParam("accountId") String accountId) {
        LOG.info("GET /book/rating?bookId=" + bookId + "&accountId=" + accountId);
        Float result = bookDAO.getAccountBookRating(bookId, accountId);
        return new ResponseEntity<Float>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Searchs for all books with the given genre
     * @param genreId the genre id
     * @return a list of books
     */
    @GetMapping("/genre/{genreId}")
    public ResponseEntity<List<Book>> searchGenre(@PathVariable("genreId") String genreId) {
        LOG.info("GET /book/genre/" + genreId);
        List<Book> result = bookDAO.searchGenre(genreId);
        return new ResponseEntity<List<Book>>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Searches for all books with the given contributor
     * @param contributorId the contributor id
     * @return a list of books
     */
    @GetMapping("/contributor/{contributorId}")
    public ResponseEntity<List<Book>> searchContributor(@PathVariable("contributorId") String contributorId) {
        LOG.info("GET /book/contributor/" + contributorId);
        List<Book> result = bookDAO.searchContributor(contributorId);
        return new ResponseEntity<List<Book>>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Searches for all books with the given format
     * @param formatId the format id
     * @return a list of books
     */
    @GetMapping("/format/{formatId}")
    public ResponseEntity<List<Book>> searchFormat(@PathVariable("formatId") String formatId) {
        LOG.info("GET /book/format/" + formatId);
        List<Book> result = bookDAO.searchFormat(formatId);
        return new ResponseEntity<List<Book>>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Searcjes for all books with the given audience
     * @param audienceId the audience id
     * @return a list of books
     */
    @GetMapping("/audience/{audienceId}")
    public ResponseEntity<List<Book>> searchAudience(@PathVariable("audienceId") String audienceId) {
        LOG.info("GET /book/audience/" + audienceId);
        List<Book> result = bookDAO.searchAudience(audienceId);
        return new ResponseEntity<List<Book>>(result, result != null ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
