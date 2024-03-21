package com.teamthree.pdmapi.persistence;

import com.teamthree.pdmapi.model.Book;
import com.teamthree.pdmapi.model.BookContributor;
import com.teamthree.pdmapi.model.BookFormat;
import com.teamthree.pdmapi.model.Contributor;
import com.teamthree.pdmapi.model.Format;
import com.teamthree.pdmapi.model.Genre;

import java.util.Date;
import java.util.List;

import com.teamthree.pdmapi.model.Audience;

public interface BookDAO {

        public Book getBookId(String bookId);

        public Book getBook(String bookTitle);

        public boolean createBook(String bookId, String bookTitle);

        public boolean setBookGenre(String bookId, Genre genreId);

        public List<Genre> getBookGenres(String bookId);

        public boolean setBookContributor(String bookId, Contributor contributorId, String type);

        public List<BookContributor> getBookContributors(String bookId);

        public boolean setBookFormat(String bookId, Format formatId, int length_pages, Date release_date);

        public List<BookFormat> getBookFormats(String bookId);

        public boolean setBookAudience(String bookId, Audience audienceId);

        public List<Audience> getBookAudiences(String bookId);

        public boolean rateBook(String accountId, String bookId, Float rating);
    
        public Float getBookRating(String bookId);
        
}
