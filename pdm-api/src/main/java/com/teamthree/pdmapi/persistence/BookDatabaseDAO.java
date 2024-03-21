package com.teamthree.pdmapi.persistence;

import com.teamthree.pdmapi.model.Audience;
import com.teamthree.pdmapi.model.Book;
import com.teamthree.pdmapi.model.Contributor;
import com.teamthree.pdmapi.model.Format;
import com.teamthree.pdmapi.model.Genre;

public class BookDatabaseDAO implements BookDAO{
        
        public Book getBookId(String bookId) {
            return null;
        }

        public Book getBook(String title) {
            return null;
        }

        public boolean createBook(String bookId, String bookTitle) {
            return false;
        }

        public boolean setBookGenre(String bookId, Genre genre) {
            return false;
        }

        public Genre getBookGenre(String bookId) {
            return null;
        }

        public boolean setBookContributor(String bookId, Contributor contributor) {
            return false;
        }

        public Genre getBookContributor(String bookId) {
            return null;
        }

        public boolean setBookFormat(String bookId, Format format) {
            return false;
        }

        public Genre getBookFormat(String bookId) {
            return null;
        }

        public boolean setBookAudience(String bookId, Audience audience) {
            return false;
        }

        public Genre getBookAudience(String bookId) {
            return null;
        }

        public boolean rateBook(String accountId, String bookId, Float rating) {
            return false;
        }
    
        public Float getBookRating(String bookId) {
            return null;
        }
}
