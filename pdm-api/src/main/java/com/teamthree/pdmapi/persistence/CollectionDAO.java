package com.teamthree.pdmapi.persistence;

import com.teamthree.pdmapi.model.Book;
import com.teamthree.pdmapi.model.Collection;

public interface CollectionDAO {

    Collection[] getCollections(String userId);

    boolean addCollection(String userId, String collectionName);

    boolean addBook(String collectionId, String bookId);

    boolean removeBook(String collectionId, String bookId);

    boolean deleteCollection(String collectionId);

    Book[] searchBook(String collectionId, String str);
}
