package com.teamthree.pdmapi.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.teamthree.pdmapi.model.Book;
import com.teamthree.pdmapi.model.Collection;

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
        String query = "SELECT * FROM Collection WHERE account_id = '" + accountId + "'';";
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
        return (Collection[]) collections.toArray();
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
    public boolean addBookToCollection(String collectionId, String bookId) {
        String query = "INSERT INTO Contains VALUES('" + collectionId + ", '" + bookId + "');";
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
    public Book[] searchBook(String collectionId, String str) {
        String query = "SELECT Contains.book_id, Book.book_title FROM contains INNER JOIN Book ON Contains.book_id = Book.book_id WHERE Contains.collection_id = '" + collectionId + "' AND Book.book_title LIKE '"+ str +"%';";
        List<Book> books = new ArrayList<>();
        try{
            Statement stmt = connHandler.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs != null) {
                while(rs.next()) {
                    String bookId = rs.getString("book_id");
                    String bookTitle = rs.getString("book_title");
                    Book newBook = new Book(bookId, bookTitle);
                    books.add(newBook);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            connHandler.closeConnection();
        }
        return (Book[]) books.toArray();
    }

    /**
     * Updates a collection's name in the database
     */
    @Override
    public boolean updateCollectionName(String collectionId, String newName) {
        String query = "UPDATE Collection SET collectionName = '" + newName + "'  WHERE collectionId = '" + collectionId;
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
    public List<Map<String, Object>> getCollectionsWithDetails(String accountId) {
        String query = "SELECT c.collection_name, COUNT(distinct bc.book_id) AS num_books, SUM(bf.length_pages) AS total_pages " +
                "FROM Collection c " +
                "LEFT JOIN Contains bc ON c.collection_id = bc.collection_id " +
                "LEFT JOIN Book b ON bc.book_id = b.bookId " +
                "LEFT JOIN BookFormat bf ON b.bookId = bf.bookId " +
                "WHERE c.account_id = '" + accountId + "''" +
                "GROUP BY c.collection_id " +
                "ORDER BY c.collection_name ASC;";

        List<Map<String, Object>> collectionDetails = new ArrayList<>();
        try{
            Statement stmt = connHandler.getConnection(false).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs != null) {
                while(rs.next()) {
                    Map<String, Object> details = new HashMap<>();
                    details.put("collectionName", rs.getString("collection_name"));
                    details.put("numBooks", rs.getInt("num_books"));
                    details.put("totalPages", rs.getInt("total_pages"));
                    collectionDetails.add(details);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            connHandler.closeConnection();
        }
        return collectionDetails;
    }
}
