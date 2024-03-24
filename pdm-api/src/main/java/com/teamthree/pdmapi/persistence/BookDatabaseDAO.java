package com.teamthree.pdmapi.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.teamthree.pdmapi.model.Audience;
import com.teamthree.pdmapi.model.Book;
import com.teamthree.pdmapi.model.BookContributor;
import com.teamthree.pdmapi.model.BookFormat;
import com.teamthree.pdmapi.model.Contributor;
import com.teamthree.pdmapi.model.Format;
import com.teamthree.pdmapi.model.Genre;

public class BookDatabaseDAO implements BookDAO{

        private final ConnectionHandler ch;

        public BookDatabaseDAO(ConnectionHandler ch) {
            this.ch = ch;
        }

        private String getNewPrimaryKey(int charNum) {
            String query  = "SELECT book_id FROM book ORDER BY account_id DESC LIMIT 1;";
            int result = 0;
            try{
                Statement stmt = ch.getConnection(false).createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(!rs.next()) return "000000";
                result = Integer.parseInt(rs.getString("book_id"));
                result += 1;
            } catch(SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                ch.closeConnection();
            }
            StringBuilder sb = new StringBuilder(String.valueOf(result));
            if(sb.length() >= charNum) return sb.toString();
            while(sb.length() < charNum) {
                sb.insert(0, "0");
            }
            return sb.toString();
        }
        
        @Override
        public Book getBookId(String bookId) {
            String query = "SELECT * FROM book WHERE book_id='" + bookId + "';";
            Book book = null;
            try {
                Statement stmt = ch.getConnection(false).createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(!rs.next()) return book;
                String id = rs.getString("book_id");
                String title = rs.getString("book_title");
                book = new Book(id, title);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ch.closeConnection();
            }
            return book;
        }

        @Override
        public Book getBook(String bookTitle) {
            String query = "SELECT * FROM book WHERE book_title='" + bookTitle + "';";
            Book book = null;
            try {
                Statement stmt = ch.getConnection(false).createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(!rs.next()) return book;
                String id = rs.getString("book_id");
                String title = rs.getString("book_title");
                book = new Book(id, title);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ch.closeConnection();
            }
            return book;
        }

        @Override
        public boolean createBook(String bookId, String bookTitle) {
            String query = "INSERT INTO book VALUES('" + getNewPrimaryKey(6) + "', '" + bookId + "', '" + bookTitle + ");";
            try{
                Statement stmt = ch.getConnection(false).createStatement();
                stmt.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                ch.closeConnection();
            }
            return true;
        }

        @Override
        public boolean setBookGenre(String bookId, Genre genreId) {
            String query = "INSERT INTO book_genre VALUES('" + bookId + "', '" + genreId + "');";
            try{
                Statement stmt = ch.getConnection(false).createStatement();
                stmt.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                ch.closeConnection();
            }
            return true;
        }

        @Override
        public List<Genre> getBookGenres(String bookId) {
            String query = "SELECT * FROM genre INNER JOIN book_genre ON genre.genre_id = book_genre.genre_id WHERE book_genre.book_id='" + bookId + "';";
            List<Genre> genres = new ArrayList<>();
            try {
                Statement stmt = ch.getConnection(false).createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs != null) {
                    while(rs.next()) {
                        String genreId = rs.getString("genre_id");
                        String genreName = rs.getString("genre_name");
                        Genre genre = new Genre(genreId, genreName);
                        genres.add(genre);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                ch.closeConnection();
            }
            return genres;
        }

        @Override
        public boolean setBookContributor(String bookId, Contributor contributorId, String type) {
            String query = "INSERT INTO contributes VALUES('" + contributorId + "', '" + bookId + "', '" + type + "');";
            try{
                Statement stmt = ch.getConnection(false).createStatement();
                stmt.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                ch.closeConnection();
            }
            return true;
        }

        @Override
        public List<BookContributor> getBookContributors(String bookId) {
            String query = "SELECT * FROM contributor INNER JOIN contributes ON contributor.contributor_id = contributes.contributor_id WHERE contributes.book_id='" + bookId + "';";
            List<BookContributor> contributors = new ArrayList<>();
            try {
                Statement stmt = ch.getConnection(false).createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs != null) {
                    while(rs.next()) {
                        String contributorId = rs.getString("contributor_id");
                        String contributorName = rs.getString("contributor_name");
                        String type = rs.getString("type");
                        Contributor contributor = new Contributor(contributorId, contributorName);
                        BookContributor bookContributor = new BookContributor(bookId, contributor, type);
                        contributors.add(bookContributor);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                ch.closeConnection();
            }
            return contributors;
        }

        @Override
        public boolean setBookFormat(String bookId, Format formatId, int length_pages, Date release_date) {
            String query = "INSERT INTO book_format VALUES('" + bookId + "', '" + formatId + "', '" + length_pages + "', '" + release_date + "');";
            try{
                Statement stmt = ch.getConnection(false).createStatement();
                stmt.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                ch.closeConnection();
            }
            return true;
        }

        @Override
        public List<BookFormat> getBookFormats(String bookId) {
            String query = "SELECT * FROM format INNER JOIN book_format ON format.format_id = book_format.format_id WHERE book_format.book_id='" + bookId + "';";
            List<BookFormat> formats = new ArrayList<>();
            try {
                Statement stmt = ch.getConnection(false).createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs != null) {
                    while(rs.next()) {
                        String formatId = rs.getString("format_id");
                        String formatType = rs.getString("format_type");
                        int length_pages = Integer.parseInt(rs.getString("length_pages"));
                        Date release_date = rs.getDate("release_date");
                        Format format = new Format(formatId, formatType);
                        BookFormat bookFormat = new BookFormat(bookId, format, length_pages, release_date);
                        formats.add(bookFormat);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                ch.closeConnection();
            }
            return formats;
        }

        @Override
        public boolean setBookAudience(String bookId, Audience audienceId) {
            String query = "INSERT INTO book_format VALUES('" + bookId + "', '" + audienceId + "');";
            try{
                Statement stmt = ch.getConnection(false).createStatement();
                stmt.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                ch.closeConnection();
            }
            return true;
        }

        @Override
        public List<Audience> getBookAudiences(String bookId) {
            String query = "SELECT * FROM audience INNER JOIN book_audience ON audience.audience_id = book_audience.audience_id WHERE book_audience.book_id='" + bookId + "';";
            List<Audience> audiences = new ArrayList<>();
            try {
                Statement stmt = ch.getConnection(false).createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs != null) {
                    while(rs.next()) {
                        String audienceId = rs.getString("audience_id");
                        String audienceName = rs.getString("audience_name");
                        Audience audience = new Audience(audienceId, audienceName);
                        audiences.add(audience);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                ch.closeConnection();
            }
            return audiences;
        }

        @Override
        public boolean rateBook(String accountId, String bookId, Float rating) {
            String query = "INSERT INTO rating VALUES('" + accountId + "', '" + bookId + "', '" + rating + "');";
            try{
                Statement stmt = ch.getConnection(false).createStatement();
                stmt.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                ch.closeConnection();
            }
            return true;
        }

        public boolean updateRating(String accountId, String bookId, Float rating) {
            String query = "UPDATE rating SET rating = '" + rating + "' WHERE account_id = '" + accountId + "' AND book_id = '" + bookId + "';";
            try{
                Statement stmt = ch.getConnection(false).createStatement();
                stmt.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                ch.closeConnection();
            }
            return true;
        }
    
        @Override
        public Float getBookRatingAvg(String bookId) {
            String query = "SELECT * FROM rating WHERE book_id='" + bookId + "';";
            Float rating = null;
            try {
                Statement stmt = ch.getConnection(false).createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs != null) {
                    Float total = 0f;
                    int amt = 0;
                    while (rs.next()) {
                        total += rs.getFloat("rating");
                        amt++;
                    }
                    rating = total/amt;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                ch.closeConnection();
            }
            return rating;
        }

        public Float getAccountBookRating(String bookId, String accountId) {
            String query = "SELECT * FROM rating WHERE book_id='" + bookId + "' AND account_id = '" + accountId + "';";
            Float rating = null;
            try {
                Statement stmt = ch.getConnection(false).createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(!rs.next()) return null;
                rating = rs.getFloat("rating");
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                ch.closeConnection();
            }
            return rating;
        }
}