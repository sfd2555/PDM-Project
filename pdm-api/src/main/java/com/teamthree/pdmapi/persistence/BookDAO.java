package com.teamthree.pdmapi.persistence;

import com.teamthree.pdmapi.model.*;

import java.util.Date;

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
        public Book getBook(String bookId);

        /**
        * Retrieves all books with the given title
        * @param bookTitle the title of the book
        * @return a list of books that matches the title
        */
        public Book[] searchBook(String bookTitle);

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
        public Genre[] getBookGenres(String bookId);

        /**
         * Searches for all books with the given genre
         * @param genreId the genre id
         * @return the list of books
         */
        public Book[] searchGenre(String genreId);

        /**
         * Searches for all books with the given genre name
         * @param genreName the genre id
         * @return the list of books
         */
        public Book[] searchGenreName(String genreName);


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
        public Contributor[] getBookContributors(String bookId);

        /**
         * Searches for all books with the given contributor
         * @param contributorId the contributor id
         * @return the list of books
         */
        public Book[] searchContributor(String contributorId);

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
        public Format[] getBookFormats(String bookId);

        /**
         * Searches for all books with the given format
         * @param formatId the format id
         * @return the list of books
         */
        public Book[] searchFormat(String formatId);

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
        public Audience[] getBookAudiences(String bookId);

        /**
         * Searches for all books with the given audience
         * @param audienceId the audience id
         * @return the list of books
         */
        public Book[] searchAudience(String audienceId);

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

        /**
         * Gets the top 20 most common books amongst the users who follow you
         * @param accountId id of the account we are displaying the info for
         * @return the list of 20 books in order from most to least popular
         */
        public Book[] top20FollowerBooks(String accountId);

        /**
         * Gets the top 20 most popular books in rated in the past 90 days
         * @return the list of 20 books in order from most to least popular
         */
        public Book[] top20Books90Day();

        /**
        * Gets the top 5 most popular books released this month
        * @return the list of 20 books in order from most to least popular
        */
        public Book[] top5ThisMonth();   
}
