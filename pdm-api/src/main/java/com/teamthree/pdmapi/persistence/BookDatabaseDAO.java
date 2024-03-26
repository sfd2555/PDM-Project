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

/**
 * A book Data Access Object that gets it's data through an SQL database
 * 
 * @author Caiden Williams
 */
public class BookDatabaseDAO implements BookDAO{

        private final ConnectionHandler ch;

        /**
        * Creates a new Book Data Access Object that connects to a SQL database
        * 
        * @param ch connection handler to database
        */
        public BookDatabaseDAO(ConnectionHandler ch) {
            this.ch = ch;
        }

        /**
        * Creates a new, unique, primary key for an object in the database
        * @param charNum how many characters to make the key
        * @return a new primary key
        */
        private String getNewPrimaryKey(int charNum) {
            // Create a new query
            String query  = "SELECT book_id FROM book ORDER BY book_id DESC LIMIT 1;";
            int result = 0;
            try{
                // Execute Query
               Statement stmt = ch.getConnection(false).createStatement();
                ResultSet rs = stmt.executeQuery(query);
               // Make sure something is returned, if not we can be assured nothing is in the database and return 0
                if(!rs.next()) return "000000";
                // Convert the results to an integer and increment it by 1
                result = Integer.parseInt(rs.getString("book_id"));
                result += 1;
            } catch(SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                ch.closeConnection();
            }
            // Add leading zeros to string
            StringBuilder sb = new StringBuilder(String.valueOf(result));
            if(sb.length() >= charNum) return sb.toString();
            while(sb.length() < charNum) {
                sb.insert(0, "0");
            }
            return sb.toString();
        }
        
        /**
         * {@inheritDoc}
        */
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

        /**
         * {@inheritDoc}
        */
        @Override
        public List<Book> getBook(String bookTitle) {
            String query = "SELECT * FROM book WHERE book_title LIKE '%" + bookTitle + "%';";
            System.out.println(query);
            List<Book> books = new ArrayList<>();
            try {
                Statement stmt = ch.getConnection(false).createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs != null) {
                    while (rs.next()) {
                        String id = rs.getString("book_id");
                        String title = rs.getString("book_title");
                        Book book = new Book(id, title);
                        books.add(book);
                    }
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ch.closeConnection();
            }
            return books;
        }

        /**
         * {@inheritDoc}
        */
        @Override
        public boolean createBook(String bookTitle) {
            String query = "INSERT INTO book VALUES('" + getNewPrimaryKey(6) + "', '" + bookTitle + "');";
            try{
                Statement stmt = ch.getConnection(false).createStatement();
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                ch.closeConnection();
            }
            return true;
        }

        /**
         * {@inheritDoc}
        */
        @Override
        public boolean setBookGenre(String bookId, String genreId) {
            String query = "INSERT INTO book_genre VALUES('" + bookId + "', '" + genreId + "');";
            try{
                Statement stmt = ch.getConnection(false).createStatement();
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                ch.closeConnection();
            }
            return true;
        }

        /**
         * {@inheritDoc}
        */
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

        /**
         * {@inheritDoc}
        */
        @Override
        public boolean setBookContributor(String bookId, String contributorId, String type) {
            String query = "INSERT INTO contributes VALUES('" + contributorId + "', '" + bookId + "', '" + type + "');";
            try{
                Statement stmt = ch.getConnection(false).createStatement();
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                ch.closeConnection();
            }
            return true;
        }

        /**
         * {@inheritDoc}
        */
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

        /**
         * {@inheritDoc}
        */
        @Override
        public boolean setBookFormat(String bookId, String formatId, int length_pages, Date release_date) {
            String query = "INSERT INTO book_format VALUES('" + bookId + "', '" + formatId + "', '" + length_pages + "', '" + release_date + "');";
            try{
                Statement stmt = ch.getConnection(false).createStatement();
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                ch.closeConnection();
            }
            return true;
        }

        /**
         * {@inheritDoc}
        */
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

        /**
         * {@inheritDoc}
        */
        @Override
        public boolean setBookAudience(String bookId, String audienceId) {
            String query = "INSERT INTO book_audience VALUES('" + bookId + "', '" + audienceId + "');";
            try{
                Statement stmt = ch.getConnection(false).createStatement();
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                ch.closeConnection();
            }
            return true;
        }

        /**
         * {@inheritDoc}
        */
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

        /**
         * {@inheritDoc}
        */
        @Override
        public boolean rateBook(String accountId, String bookId, Float rating) {
            if (getAccountBookRating(bookId, accountId) != null) {
                return updateRating(accountId, bookId, rating);
            }
            String query = "INSERT INTO rating VALUES('" + accountId + "', '" + bookId + "', '" + rating + "');";
            try{
                Statement stmt = ch.getConnection(false).createStatement();
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                ch.closeConnection();
            }
            return true;
        }

        /**
         * {@inheritDoc}
        */
        private boolean updateRating(String accountId, String bookId, Float rating) {
            String query = "UPDATE rating SET rating = '" + rating + "' WHERE account_id = '" + accountId + "' AND book_id = '" + bookId + "';";
            try{
                Statement stmt = ch.getConnection(false).createStatement();
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                ch.closeConnection();
            }
            return true;
        }
    
        /**
         * {@inheritDoc}
        */
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

        /**
         * {@inheritDoc}
        */
        @Override
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

        /**
         * {@inheritDoc}
        */
        @Override
        public List<Book> searchGenre(String genreId) {
            String query = "SELECT * FROM book INNER JOIN book_genre ON book.book_id = book_genre.book_id WHERE book_genre.genre_id='" + genreId + "';";
            List<Book> books = new ArrayList<>();
            try {
                Statement stmt = ch.getConnection(false).createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs != null) {
                    while(rs.next()) {
                        String bookId = rs.getString("book_id");
                        String bookTitle = rs.getString("book_title");
                        Book book = new Book(bookId, bookTitle);
                        books.add(book);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                ch.closeConnection();
            }
            return books;
        }

        /**
         * {@inheritDoc}
        */
        @Override
        public List<Book> searchContributor(String contributorId) {
            String query = "SELECT * FROM book INNER JOIN book_contributor ON book.book_id = book_contributor.book_id WHERE book_contributor.contributor_id='" + contributorId + "';";
            List<Book> books = new ArrayList<>();
            try {
                Statement stmt = ch.getConnection(false).createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs != null) {
                    while(rs.next()) {
                        String bookId = rs.getString("book_id");
                        String bookTitle = rs.getString("book_title");
                        Book book = new Book(bookId, bookTitle);
                        books.add(book);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                ch.closeConnection();
            }
            return books;
        }

        /**
         * {@inheritDoc}
        */
        @Override
        public List<Book> searchFormat(String formatId) {
            String query = "SELECT * FROM book INNER JOIN book_format ON book.book_id = book_format.book_id WHERE book_format.format_id='" + formatId + "';";
            List<Book> books = new ArrayList<>();
            try {
                Statement stmt = ch.getConnection(false).createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs != null) {
                    while(rs.next()) {
                        String bookId = rs.getString("book_id");
                        String bookTitle = rs.getString("book_title");
                        Book book = new Book(bookId, bookTitle);
                        books.add(book);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                ch.closeConnection();
            }
            return books;
        }

        /**
         * {@inheritDoc}
        */
        @Override
        public List<Book> searchAudience(String audienceId) {
            String query = "SELECT * FROM book INNER JOIN book_audience ON book.book_id = book_audience.book_id WHERE book_audience.audience_id='" + audienceId + "';";
            List<Book> books = new ArrayList<>();
            try {
                Statement stmt = ch.getConnection(false).createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs != null) {
                    while(rs.next()) {
                        String bookId = rs.getString("book_id");
                        String bookTitle = rs.getString("book_title");
                        Book book = new Book(bookId, bookTitle);
                        books.add(book);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                ch.closeConnection();
            }
            return books;
        }
}
