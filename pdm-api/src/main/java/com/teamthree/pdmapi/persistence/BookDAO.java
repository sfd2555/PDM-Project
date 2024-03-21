package com.teamthree.pdmapi.persistence;

import com.teamthree.pdmapi.model.Book;
import com.teamthree.pdmapi.model.Contributor;
import com.teamthree.pdmapi.model.Format;
import com.teamthree.pdmapi.model.Genre;
import com.teamthree.pdmapi.model.Audience;


public interface BookDAO {

        public Book getBookId(String bookId);

        public Book getBook(String title);

        public boolean createBook(String bookId, String bookTitle);

        public boolean setBookGenre(String bookId, Genre genre);

        public Genre getBookGenre(String bookId);

        public boolean setBookContributor(String bookId, Contributor contributor);

        public Genre getBookContributor(String bookId);

        public boolean setBookFormat(String bookId, Format format);

        public Genre getBookFormat(String bookId);

        public boolean setBookAudience(String bookId, Audience audience);

        public Genre getBookAudience(String bookId);

        public boolean rateBook(String accountId, String bookId, Float rating);
    
        public Float getBookRating(String bookId);
        
}
