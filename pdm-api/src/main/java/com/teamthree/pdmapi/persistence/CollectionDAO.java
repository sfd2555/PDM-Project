package com.teamthree.pdmapi.persistence;

import com.teamthree.pdmapi.model.Collection;
import com.teamthree.pdmapi.model.CollectionMetadata;
import com.teamthree.pdmapi.model.BookCollectionMetadata;

/**
 * Allows access to a collection's data
 * 
 * @author Sean Droll, Beining Zhou
 */
public interface CollectionDAO {

    /**
     * Retrieves a list of a user's collections
     * @param accountId id of user to retrieve collections of
     * @return a list of the user's collections
     */
    Collection[] getCollections(String accountId);

    Collection getCollection(String collectionId);

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
    boolean addBookToCollection(String collectionId, String bookId, String formatId);

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
    BookCollectionMetadata[] searchBook(String collectionId, String str);

    /**
     * Updates the name of an existing collection.
     * @param collectionId id of the collection to update
     * @param newName new name for the collection
     * @return true upon success
     */
    boolean updateCollectionName(String collectionId, String newName);

    /**
     * Retrieves detailed information about all collections belonging to a user.
     * @param accountId The ID of the user whose collections are being retrieved.
     * @return A list of maps, each containing details about a collection.
     */
    CollectionMetadata[] getCollectionMetadata(String accountId);
}
