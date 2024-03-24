package com.teamthree.pdmapi.persistence;

import com.teamthree.pdmapi.model.Book;
import com.teamthree.pdmapi.model.Collection;

/**
 * Allows access to a collection's data
 * 
 * @author Sean Droll
 */
public interface CollectionDAO {

    /**
     * Retrieves a list of a user's collections
     * @param accountId id of user to retrieve collections of
     * @return a list of the user's collections
     */
    Collection[] getCollections(String accountId);

    /**
     * Creates a new collection assigned to a user
     * @param accountId id of user to assign new collection to
     * @param collectionName name of new collection
     * @return true upon success
     */
    boolean createCollection(String accountId, String collectionName);

    /**
     * Adds a book to an existing collection
     * @param collectionId id of collection to add to
     * @param bookId id of book to add to collection
     * @return true upon success
     */
    boolean addBookToCollection(String collectionId, String bookId);

    /**
     * Removes a book from a collection
     * @param collectionId id of collection to remove from
     * @param bookId id of book to remove from collection
     * @return true upon success
     */
    boolean removeBook(String collectionId, String bookId);

    /**
     * Deletes an existing collection
     * @param collectionId id of collection to remove
     * @return true upon success
     */
    boolean deleteCollection(String collectionId);


    /**
     * Returns a list of books contained in a collection that match the provided string
     * @param collectionId id of collection to search from
     * @param str string to search
     * @return list of books matching search parameters
     */
    Book[] searchBook(String collectionId, String str);
}
