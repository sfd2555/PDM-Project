package com.teamthree.pdmapi.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.teamthree.pdmapi.model.*;

/**
 * A collection Data Access Object that gets it's data through an SQL database
 * 
 * @author Sean Droll, Beining Zhou
 */
public class CollectionDatabaseDAO implements CollectionDAO{
    private final ConnectionHandler connHandler;

    /**
     * Creates a new collection Data Access Object that connects to a SQL database
     * 
     * @param connHandler connection handler to database
     */
    public CollectionDatabaseDAO(ConnectionHandler connHandler) {
        this.connHandler = connHandler;
    }

    /**
     * Creates a new, unique, primary key for an object in the database
     * @param charNum how many characters to make the key
     * @return a new primary key
     */
    private String getNewPrimaryKey(int charNum) {
        String query  = "SELECT collection_id FROM collection ORDER BY collection_id DESC LIMIT 1;";
        int result = 0;
        try{
            Statement stmt = connHandler.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()) return "000000";
            result = Integer.parseInt(rs.getString("collection_id"));
            System.out.println(result);
            result += 1;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            connHandler.closeConnection();
        }
        StringBuilder sb = new StringBuilder(String.valueOf(result));
        if(sb.length() >= charNum) return sb.toString();
        while(sb.length() < charNum) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

    /*
     * Gets a list of collections matching the given account id from the database
     */
    public Collection[] getCollections(String accountId) {
        String query = "SELECT * FROM Collection WHERE account_id = '" + accountId + "';";
        List<Collection> collections = new ArrayList<>();
        try{
            Statement stmt = connHandler.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs != null) {
                while(rs.next()) {
                    String collectionId = rs.getString("collection_id");
                    String collectionName = rs.getString("collection_name");
                    Collection newCollection = new Collection(collectionId, accountId, collectionName);
                    collections.add(newCollection);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            connHandler.closeConnection();
        }
        return collections.toArray(new Collection[0]);
    }

    public Collection getCollection(String collectionId) {
        String query = "SELECT * FROM Collection WHERE collection_id = '" + collectionId + "';";
        Collection collection = null;
        try{
            Statement stmt = connHandler.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs != null) {
                while(rs.next()) {
                    String accountId = rs.getString("account_id");
                    String collectionName = rs.getString("collection_name");
                    collection = new Collection(collectionId, accountId, collectionName);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            connHandler.closeConnection();
        }
        return collection;
    }

    /**
     * Creates a new database and adds it to the database
     */
    public boolean createCollection(String accountId, String collectionName) {
        String newPrimaryKey = getNewPrimaryKey(6);
        if(newPrimaryKey == null) {
            return false;
        }
        String query = "INSERT INTO Collection VALUES('" + newPrimaryKey + "', '" + accountId + "', '" + collectionName + "');";
        try{
            Statement stmt = connHandler.getConnection(false).createStatement();
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connHandler.closeConnection();
        }
        return true;
    }

    /**
     * Adds a book to an existing collection in the database
     */
    public boolean addBookToCollection(String collectionId, String bookId, String formatId) {
        String query = "INSERT INTO Contains VALUES('" + collectionId + "', '" + bookId + "', '" + formatId +"');";
        try{
            Statement stmt = connHandler.getConnection(false).createStatement();
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connHandler.closeConnection();
        }
        return true;
    }

    /**
     * Removes a book from an existing databse
     */
    public boolean removeBook(String collectionId, String bookId) {
        String query = "DELETE FROM Contains WHERE collection_id='" + collectionId + "' AND book_id='" + bookId + "';";
        try{
            Statement stmt = connHandler.getConnection(false).createStatement();
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connHandler.closeConnection();
        }
        return true;
    }

    /**
     * Deletes an existing collection from the database
     */
    public boolean deleteCollection(String collectionId) {
        String query = "DELETE FROM Collection WHERE collection_id='" + collectionId + "';";
        try{
            Statement stmt = connHandler.getConnection(false).createStatement();
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connHandler.closeConnection();
        }
        return true;
    }

    /**
     * Searches for a book in the database
     */
    public BookCollectionMetadata[] searchBook(String collectionId, String str) {
        if(str == null) {
            str = "";
        }
        String query = "SELECT c.collection_id, c.book_id, b.book_title, f.format_type, bf.length_pages " +
                "FROM Contains AS c " +
                "INNER JOIN Book AS b ON c.book_id = b.book_id " +
                "INNER JOIN Format AS f ON c.format_id = f.format_id " +
                "INNER JOIN book_format AS bf ON c.book_id = bf.book_id AND c.format_id = bf.format_id " +
                "WHERE c.collection_id = '" + collectionId + "' " +
                "AND b.book_title LIKE '%" + str + "%';";
        List<BookCollectionMetadata> books = new ArrayList<>();
        try{
            Statement stmt = connHandler.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs != null) {
                while(rs.next()) {
                    String bookId = rs.getString("book_id");
                    String bookTitle = rs.getString("book_title");
                    String formatType = rs.getString("format_type");
                    int length = rs.getInt("length_pages");
                    Genre[] genres = getBookGenres(bookId);
                    Contributor[] contributors = getBookContributors(bookId);
                    System.out.println(contributors.length);
                    BookCollectionMetadata newContains = new BookCollectionMetadata(collectionId, bookId, formatType, bookTitle, length, genres, contributors);
                    books.add(newContains);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            connHandler.closeConnection();
        }
        return books.toArray(new BookCollectionMetadata[0]);
    }

    /**
     * Updates a collection's name in the database
     */
    @Override
    public boolean updateCollectionName(String collectionId, String newName) {
        String query = "UPDATE collection SET collection_name='" + newName + "' WHERE collection_id='" + collectionId + "';";
        try{
            Statement stmt = connHandler.getConnection(false).createStatement();
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connHandler.closeConnection();
        }
        return true;
    }

    /**
     * Displays details of collections in the database
     * Includes collection’s name,number of books and total length of the books in the collection
     * Displat collections by name in ascending order
     */
    @Override
    public CollectionMetadata[] getCollectionMetadata(String accountId) {
        String query = "SELECT co.collection_id, co.account_id, co.collection_name, COUNT(DISTINCT ca.book_id) AS entries, COALESCE(SUM(bf.length_pages), 0) AS volume " +
                "FROM collection AS co " +
                "LEFT JOIN contains AS ca ON co.collection_id = ca.collection_id " +
                "LEFT JOIN book_format AS bf ON ca.book_id = bf.book_id AND ca.format_id = bf.format_id " +
                "WHERE co.account_id = '" + accountId + "' " +
                "GROUP BY co.collection_id " +
                "ORDER BY co.collection_name;";
        List<CollectionMetadata> collections = new ArrayList<>();
        try{
            Statement stmt = connHandler.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                String collection_id = rs.getString("collection_id");
                String collection_name = rs.getString("collection_name");
                int entries = rs.getInt("entries");
                int volume = rs.getInt("volume");
                CollectionMetadata collection = new CollectionMetadata(collection_id, accountId, collection_name, entries, volume);
                collections.add(collection);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            connHandler.closeConnection();
        }
        return collections.toArray(new CollectionMetadata[0]);
    }


    private Contributor[] getBookContributors(String bookId) {
        String query = "SELECT cts.contributor_id, ctb.contributor_name, cts.type FROM contributes as cts INNER JOIN contributor ctb ON cts.contributor_id = ctb.contributor_id WHERE cts.book_id='" + bookId + "';";
        List<Contributor> contributors = new ArrayList<>();
            try {
                Statement stmt = connHandler.getConnection(false).createStatement();
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
            }
            return contributors.toArray(new Contributor[0]);
    }

    private Genre[] getBookGenres(String bookId) {
        String query = "SELECT g.genre_id, g.genre_name " +
                "FROM book_genre AS bg " +
                "INNER JOIN genre AS g ON bg.genre_id = g.genre_id " +
                "WHERE bg.book_id='" + bookId + "';";
        List<Genre> genres = new ArrayList<>();
        try {
            Statement stmt = connHandler.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String genreId = rs.getString("genre_id");
                String genreName = rs.getString("genre_name");
                Genre genre = new Genre(genreId, genreName);
                genres.add(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            connHandler.closeConnection();
        }
        return genres.toArray(new Genre[0]);
    }
}
