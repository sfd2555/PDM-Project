package com.teamthree.pdmapi.persistence;

import com.teamthree.pdmapi.model.Book;
import com.teamthree.pdmapi.model.BookContributor;
import com.teamthree.pdmapi.model.BookFormat;
import com.teamthree.pdmapi.model.Genre;

import java.util.Date;
import java.util.List;

import com.teamthree.pdmapi.model.Audience;

public interface BookDAO {

        public Book getBookId(String bookId);

        public Book getBook(String bookTitle);

        public boolean createBook(String bookTitle);

        public boolean setBookGenre(String bookId, String genreId);

        public List<Genre> getBookGenres(String bookId);

        public boolean setBookContributor(String bookId, String contributorId, String type);

        public List<BookContributor> getBookContributors(String bookId);

        public boolean setBookFormat(String bookId, String formatId, int length_pages, Date release_date);

        public List<BookFormat> getBookFormats(String bookId);

        public boolean setBookAudience(String bookId, String audienceId);

        public List<Audience> getBookAudiences(String bookId);

        public boolean rateBook(String accountId, String bookId, Float rating);
    
        public Float getBookRatingAvg(String bookId);

        public Float getAccountBookRating(String bookId, String accountId);
        
}
