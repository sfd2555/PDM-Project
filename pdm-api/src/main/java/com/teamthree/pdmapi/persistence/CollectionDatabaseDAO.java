package com.teamthree.pdmapi.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.teamthree.pdmapi.model.Book;
import com.teamthree.pdmapi.model.Collection;

public class CollectionDatabaseDAO implements CollectionDAO{
    private final ConnectionHandler connHandler;

    public CollectionDatabaseDAO(ConnectionHandler connHandler) {
        this.connHandler = connHandler;
    }

    private String getNewPrimaryKey(int charNum) {
        String query  = "SELECT collection_id FROM collection ORDER BY collection_id DESC LIMIT 1;";
        int result = 0;
        try{
            Statement stmt = connHandler.getConnection(true).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()) return "000000";
            result = Integer.parseInt(rs.getString("collection_id"));
            System.out.println(result);
            result += 1;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(result));
        if(sb.length() >= charNum) return sb.toString();
        while(sb.length() < charNum) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

    public Collection[] getCollections(String userId) {
        String query = "SELECT * FROM Collection WHERE user_id = " + userId + ";";
        List<Collection> collections = new ArrayList<>();
        try{
            Statement stmt = connHandler.getConnection(true).createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs != null) {
                while(rs.next()) {
                    String collectionId = rs.getString("collection_id");
                    String collectionName = rs.getString("collection_name");
                    Collection newCollection = new Collection(collectionId, userId, collectionName);
                    collections.add(newCollection);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        return (Collection[]) collections.toArray();
    }

    public boolean addCollection(String userId, String collectionName) {
        String query = "INSERT INTO Collection VALUES('" + getNewPrimaryKey(6) + "', '" + userId + "', '" + collectionName + "');";
        try{
            Statement stmt = connHandler.getConnection(true).createStatement();
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addBook(String collectionId, String bookId) {
        String query = "INSERT INTO Contains VALUES('" + collectionId + ", '" + bookId + "');";
        try{
            Statement stmt = connHandler.getConnection(true).createStatement();
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeBook(String collectionId, String bookId) {
        String query = "DELETE FROM Contains WHERE collection_id='" + collectionId + "' AND book_id='" + bookId + "';";
        try{
            Statement stmt = connHandler.getConnection(true).createStatement();
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteCollection(String collectionId) {
        String query = "DELETE FROM Collection WHERE collection_id='" + collectionId + "';";
        try{
            Statement stmt = connHandler.getConnection(true).createStatement();
            stmt.executeUpdate(query);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Book[] searchBook(String collectionId, String str) {
        String query = "SELECT Contains.book_id, Book.book_title FROM contains INNER JOIN Book ON Contains.book_id = Book.book_id WHERE Contains.collection_id = '" + collectionId + "' AND Book.book_title LIKE '"+ str +"%';";
        List<Book> books = new ArrayList<>();
        try{
            Statement stmt = connHandler.getConnection(true).createStatement();
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
        }
        return (Book[]) books.toArray();
    }
    
}
