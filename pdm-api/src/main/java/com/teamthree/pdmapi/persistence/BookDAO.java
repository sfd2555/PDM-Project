package com.teamthree.pdmapi.persistence;

import com.teamthree.pdmapi.model.Book;
import com.teamthree.pdmapi.model.BookContributor;
import com.teamthree.pdmapi.model.BookFormat;
import com.teamthree.pdmapi.model.Genre;

import java.util.Date;
import java.util.List;

import com.teamthree.pdmapi.model.Audience;

/**
 * Allows access to a books data
 * 
 * @author Caiden Williams
 */
public interface BookDAO {

        /**
        * Retrieves a book via a bookId
        * @param bookId the Id of the book
        * @return a book that matches the Id (if there is one)
        */
        public Book getBookId(String bookId);

        /**
        * Retrieves all books with the given title
        * @param bookTitle the title of the book
        * @return a list of books that matches the title
        */
        public List<Book> getBook(String bookTitle);

        /**
        * Creates a book with a given title
        * @param bookTitle the title of the book
        * @return whether or not the creation was successful
        */
        public boolean createBook(String bookTitle);

        /**
        * Sets a genre for the book
        * @param bookId the id of the book
        * @param genreId the id of the genre
        * @return whether or not the creation was successful
        */
        public boolean setBookGenre(String bookId, String genreId);

        /**
        * Gets all of the genres for the book
        * @param bookId the id of the book
        * @return the list of genres
        */
        public List<Genre> getBookGenres(String bookId);

        /**
         * Searches for all books with the given genre
         * @param genreId the genre id
         * @return the list of books
         */
        public List<Book> searchGenre(String genreId);

        /**
        * Sets a contributor for the book and how they contributed
        * @param bookId the id of the book
        * @param contributorId the id of the contributor
        * @param type how the contributor contributed
        * @return whether or not the creation was successful
        */
        public boolean setBookContributor(String bookId, String contributorId, String type);

        /**
        * Gets all of the contributors for the book
        * @param bookId the id of the book
        * @return the list of contributors
        */
        public List<BookContributor> getBookContributors(String bookId);

        /**
         * Searches for all books with the given contributor
         * @param contributorId the contributor id
         * @return the list of books
         */
        public List<Book> searchContributor(String contributorId);

        /**
        * Sets a format for the book, how long it is, and when it was released
        * @param bookId the id of the book
        * @param formatId the id of the format
        * @param length_pages how long the book is
        * @param release_date when the book was released
        * @return whether or not the creation was successful
        */
        public boolean setBookFormat(String bookId, String formatId, int length_pages, Date release_date);

        /**
        * Gets all of the formats for the book
        * @param bookId the id of the book
        * @return the list of formats
        */
        public List<BookFormat> getBookFormats(String bookId);

        /**
         * Searches for all books with the given format
         * @param formatId the format id
         * @return the list of books
         */
        public List<Book> searchFormat(String formatId);

        /**
        * Sets a audience for the book
        * @param bookId the id of the book
        * @param audienceId the id of the audience
        * @return whether or not the creation was successful
        */
        public boolean setBookAudience(String bookId, String audienceId);

        /**
        * Gets all of the audiences for the book
        * @param bookId the id of the book
        * @return the list of audiences
        */
        public List<Audience> getBookAudiences(String bookId);

        /**
         * Searches for all books with the given audience
         * @param audienceId the audience id
         * @return the list of books
         */
        public List<Book> searchAudience(String audienceId);

        /**
        * Rates a book from the given accountId
        * @param accountId the id of the account
        * @param book the id of the book to be rated
        * @param rating the rating for the book
        * @return whether or not the rating was successful
        */
        public boolean rateBook(String accountId, String bookId, Float rating);
    
        /**
        * Gets the average rating for a book
        * @param bookId the id of the book
        * @return the average rating
        */
        public Float getBookRatingAvg(String bookId);

        /**
        * Gets the rating for the book from a specific user
        * @param bookId the id of the book
        * @param accountId the id of the account
        * @return the user's rating for the book
        */
        public Float getAccountBookRating(String bookId, String accountId);
        
}
