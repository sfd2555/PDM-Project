package com.teamthree.pdmapi.controller;

import com.teamthree.pdmapi.model.Collection;
import com.teamthree.pdmapi.model.CollectionMetadata;
import com.teamthree.pdmapi.model.BookCollectionMetadata;
import com.teamthree.pdmapi.persistence.CollectionDAO;
import com.teamthree.pdmapi.persistence.CollectionDatabaseDAO;
import com.teamthree.pdmapi.persistence.ConnectionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

/**
 * Handles web requests for managing collections, including creating, updating, viewing, and deleting collections.
 * It also supports operations for adding and removing books within collections, integrating with CollectionDAO
 * for data access.
 *
 * @author Beining Zhou
 */
@Controller
@RequestMapping("collection")
public class CollectionController {
    private static final Logger LOG = Logger.getLogger(CollectionController.class.getName());
    private CollectionDAO collectionDAO;


        /**
     * Handles REST API requests for account resorces
     * @param ch a connection handler to the database
     */
    private CollectionController(ConnectionHandler ch) {
        collectionDAO = new CollectionDatabaseDAO(ch);
    }

    /**
     * Creates a new collection
     * @param accountId id of an account
     * @param collectionName name of a collection
     * @return ResponseEntity of true upon success
     *         ResponseEntity of false upon failure
     */
    @PostMapping("/create/{accountId}")
    @ResponseBody
    public ResponseEntity<Boolean> createCollection(
            @PathVariable("accountId") String accountId,
            @RequestParam("collectionName") String collectionName) {
        LOG.info("POST /collection " + accountId + " " + collectionName);
        boolean result = collectionDAO.createCollection(accountId, collectionName);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Deletes a collection
     * @param collectionId name of a collection
     * @return ResponseEntity of true upon success
     *         ResponseEntity of false upon failure
     */
    @DeleteMapping("/{collectionId}")
    @ResponseBody
    public ResponseEntity<Boolean> deleteCollection(@PathVariable("collectionId") String collectionId) {
        LOG.info("DELETE /collection/" + collectionId);
        boolean result = collectionDAO.deleteCollection(collectionId);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Gets all collections of a user
     * @param accountId id of an account
     * @return ResponseEntity containing a list of collection(s) upon success
     *         ResponseEntity with code NOT_FOUND if the account does not exist
     */
    @GetMapping("/account/{accountId}")
    @ResponseBody
    public ResponseEntity<Collection[]> getCollections(@PathVariable("accountId") String accountId) {
        LOG.info("GET /collection/" + accountId);
        Collection[] collections = collectionDAO.getCollections(accountId);
        if(collections == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(collections, HttpStatus.OK);
    }

    @GetMapping("/{collectionId}")
    @ResponseBody
    public ResponseEntity<Collection> getCollection(@PathVariable("collectionId") String collectionId) {
        LOG.info("GET /collection/" + collectionId);
        Collection collection = collectionDAO.getCollection(collectionId);
        if(collection == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @GetMapping("/contents/{collectionId}")
    @ResponseBody
    public ResponseEntity<BookCollectionMetadata[]> searchCollectionContents(@PathVariable("collectionId") String collectionId, @RequestParam(name = "", required = false, defaultValue = "") String str) {
        LOG.info("GET /collection/contents/" + collectionId + "?str=" + str);
        BookCollectionMetadata[] contents = collectionDAO.searchBook(collectionId, str);
        if(contents == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    /**
     * Adds a book to a collection
     * @param collectionId name of a collection
     * @param bookId id of a book
     * @return ResponseEntity of true upon success
     *         ResponseEntity of false upon failure
     */
    @PostMapping("/{collectionId}")
    @ResponseBody
    public ResponseEntity<Boolean> addBookToCollection(
            @PathVariable("collectionId") String collectionId,
            @RequestParam("bookId") String bookId, @RequestParam("formatId") String formatId) {
        LOG.info("POST /collection/" + collectionId + "?bookId=" + bookId + "&formatId=" + formatId);
        boolean result = collectionDAO.addBookToCollection(collectionId, bookId, formatId);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Deletes a book from a collection
     * @param collectionId name of a collection
     * @param bookId id of a book
     * @return ResponseEntity of true upon success
     *         ResponseEntity of false upon failure
     */
    @DeleteMapping("/removeBook")
    @ResponseBody
    public ResponseEntity<Boolean> removeBook(
            @RequestParam("collectionId") String collectionId,
            @RequestParam("bookId") String bookId) {
        LOG.info("DELETE /collection/removeBook?collectionId=" + collectionId + "&bookId=" + bookId);
        boolean result = collectionDAO.removeBook(collectionId, bookId);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Deletes a book from a collection
     * @param collectionId name of a collection
     * @param newName new name of a collection
     * @return ResponseEntity of true upon success
     *         ResponseEntity of false upon failure
     */
    @PutMapping("/updateName")
    @ResponseBody
    public ResponseEntity<Boolean> updateCollectionName(
            @RequestParam("collectionId") String collectionId,
            @RequestParam("newName") String newName) {
        LOG.info("PUT /collection/updateName?collectionId=" + collectionId + "&newName=" + newName);
        boolean result = collectionDAO.updateCollectionName(collectionId, newName);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Gets detailed information about all collections of a user.
     * @param accountId The account ID of the user.
     * @return ResponseEntity containing the collection details.
     *         ResponseEntity with code NOT_FOUND if the account does not exist
     */
    @GetMapping("/metadata/{accountId}")
    public ResponseEntity<CollectionMetadata[]> getCollectionsDetails(
            @PathVariable("accountId") String accountId) {
        LOG.info("GET /collection/details/" + accountId);
        CollectionMetadata[] metadata = collectionDAO.getCollectionMetadata(accountId);
        if(metadata == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(metadata, HttpStatus.OK);
    }
}
