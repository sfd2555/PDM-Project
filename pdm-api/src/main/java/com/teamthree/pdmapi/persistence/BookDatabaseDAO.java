package com.teamthree.pdmapi.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.teamthree.pdmapi.model.Audience;
import com.teamthree.pdmapi.model.Book;
import com.teamthree.pdmapi.model.Contributor;
import com.teamthree.pdmapi.model.Format;
import com.teamthree.pdmapi.model.Genre;

/**
 * A book Data Access Object that gets it's data through an SQL database
 * 
 * @author Caiden Williams, Sean Droll
 */
public class BookDatabaseDAO implements BookDAO {

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
        public Book getBook(String bookId) {
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
        public Book[] searchBook(String bookTitle) {
            String query = "SELECT * FROM book WHERE book_title LIKE '" + bookTitle + "%';";
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
            return books.toArray(new Book[0]);
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
        public Genre[] getBookGenres(String bookId) {
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
            return genres.toArray(new Genre[0]);
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
        public Contributor[] getBookContributors(String bookId) {
            String query = "SELECT cts.contributor_id, ctb.contributor_name, cts.type FROM contributes as cts INNER JOIN contributor ctb ON cts.contributor_id = ctb.contributor_id WHERE cts.book_id='" + bookId + "';";
            List<Contributor> contributors = new ArrayList<>();
            try {
                Statement stmt = ch.getConnection(false).createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs != null) {
                    while(rs.next()) {
                        String contributorId = rs.getString("contributor_id");
                        String contributorName = rs.getString("contributor_name");
                        String contributorType = rs.getString("type");
                        Contributor contributor = new Contributor(contributorId, contributorName, contributorType);
                        contributors.add(contributor);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                ch.closeConnection();
            }
            return contributors.toArray(new Contributor[0]);
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
        public Format[] getBookFormats(String bookId) {
            String query = "SELECT * FROM format INNER JOIN book_format ON format.format_id = book_format.format_id WHERE book_format.book_id='" + bookId + "';";
            List<Format> formats = new ArrayList<>();
            try {
                Statement stmt = ch.getConnection(false).createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs != null) {
                    while(rs.next()) {
                        String formatId = rs.getString("format_id");
                        String formatType = rs.getString("format_type");
                        int bookLength = rs.getInt("length_pages");
                        String releaseDate = rs.getString("release_date");
                        Format format = new Format(formatId, formatType, bookLength, releaseDate);
                        formats.add(format);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                ch.closeConnection();
            }
            return formats.toArray(new Format[0]);
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
        public Audience[] getBookAudiences(String bookId) {
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
            return audiences.toArray(new Audience[0]);
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
        public Book[] searchGenre(String genreId) {
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
            return books.toArray(new Book[0]);
        }

    @Override
    public Book[] searchGenreName(String genreName) {
        String query = "SELECT book.* FROM book INNER JOIN book_genre ON book.book_id = book_genre.book_id INNER JOIN genre ON book_genre.genre_id = genre.genre_id WHERE genre.genre_name = '" + genreName + "';";
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
        return books.toArray(new Book[0]);
    }

    /**
         * {@inheritDoc}
        */
        @Override
        public Book[] searchContributor(String contributorId) {
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
            return books.toArray(new Book[0]);
        }

        /**
         * {@inheritDoc}
        */
        @Override
        public Book[] searchFormat(String formatId) {
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
            return books.toArray(new Book[0]);
        }

        /**
         * {@inheritDoc}
        */
        @Override
        public Book[] searchAudience(String audienceId) {
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
            return books.toArray(new Book[0]);
        }

        /**
         * {@inheritDoc}
        */
        @Override
        public Book[] top20FollowerBooks(String audienceId) {
            String query = "Select book.book_title, book.book_id, COUNT(*) AS occurrences FROM book LEFT JOIN contains ON contains.book_id = book.book_id INNER JOIN collection ON collection.collection_id = contains.collection_id INNER JOIN account ON account.account_id = collection.account_id INNER JOIN friends ON friends.account2_id = account.account_id WHERE friends.account1_id = '"+audienceId+"' GROUP BY book.book_title, book.book_id ORDER BY occurrences DESC LIMIT 20";
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
            return books.toArray(new Book[0]);
        }

        /**
         * {@inheritDoc}
        */
        @Override
        public Book[] top20Books90Day() {
            String query = "Select book.book_title, book.book_id, AVG(rating) AS average_rating FROM book INNER JOIN rating ON rating.book_id = book.book_id WHERE rating.date >= CURRENT_DATE - 90 GROUP BY book.book_title, book.book_id ORDER BY average_rating DESC LIMIT 20";
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
            return books.toArray(new Book[0]);
        }

        /**
        * {@inheritDoc}
        */
        @Override
        public Book[] top5ThisMonth() {
            String query = "Select book.book_title, book.book_id, AVG(rating.rating) AS average_rating FROM book JOIN rating on rating.book_id = book.book_id JOIN book_format on book_format.book_id = book.book_id WHERE CAST(book_format.release_date AS TEXT) like CONCAT(SUBSTRING(CAST(CURRENT_DATE AS TEXT), 1, 8),'%') GROUP BY book.book_title, book.book_id ORDER BY average_rating DESC LIMIT 5";
            List<Book> books = new ArrayList<>();
            try {
                Statement stmt = ch.getConnection(false).createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs != null) {
                    while(rs.next()) {
                        System.out.println("Result");
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
            return books.toArray(new Book[0]);
        }
}
