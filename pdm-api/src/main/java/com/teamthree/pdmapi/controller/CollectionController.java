package com.teamthree.pdmapi.controller;

import com.teamthree.pdmapi.persistence.CollectionDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Handles web requests for managing collections, including creating, updating, viewing, and deleting collections.
 * It also supports operations for adding and removing books within collections, integrating with CollectionDAO
 * for data access.
 *
 * @author Beining Zhou
 */
@RestController
@RequestMapping("collection")
public class CollectionController {
    private static final Logger LOG = Logger.getLogger(CollectionController.class.getName());
    private CollectionDAO collectionDAO;

    /**
     * Creates a new collection
     * @param accountId id of an account
     * @param collectionName name of a collection
     * @return ResponseEntity of true upon success
     *         ResponseEntity of false upon failure
     */
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Boolean> createCollection(
            @RequestParam("accountId") String accountId,
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
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Collection[]> getCollections(@RequestParam("accountId") String accountId) {
        LOG.info("GET /collection " + accountId);
       Collection[] collections = collectionDAO.getCollections(accountId);
        if(collections == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(collections, HttpStatus.OK);
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
            @RequestParam("bookId") String bookId) {
        LOG.info("POST /collection/" + collectionId + "?bookId=" + bookId);
        boolean result = collectionDAO.addBookToCollection(collectionId, bookId);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    /**
     * Deletes a book from a collection
     * @param collectionId name of a collection
     * @param bookId id of a book
     * @return ResponseEntity of true upon success
     *         ResponseEntity of false upon failure
     */
    @DeleteMapping("/{collectionId}/{bookId}")
    @ResponseBody
    public ResponseEntity<Boolean> removeBook(
            @PathVariable("collectionId") String collectionId,
            @PathVariable("bookId") String bookId) {
        LOG.info("DELETE /collection/ " + collectionId + "/" + bookId);
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
    @PutMapping("/{collectionId}")
    @ResponseBody
    public ResponseEntity<Boolean> updateCollectionName(
            @PathVariable("collectionId") String collectionId,
            @RequestParam("newName") String newName) {
        LOG.info("PUT /collection/ " + collectionId + "/" + newName);
        boolean result = collectionDAO.updateCollectionName(collectionId, newName);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Gets detailed information about all collections of a user.
     * @param accountId The account ID of the user.
     * @return ResponseEntity containing the collection details.
     *         ResponseEntity with code NOT_FOUND if the account does not exist
     */
    @GetMapping("/details/{accountId}")
    public ResponseEntity<List<Map<String, Object>>> getCollectionsDetails(
            @PathVariable("accountId") String accountId) {
        LOG.info("GET /collection/details/" + accountId);
        List<Map<String, Object>> details = collectionDAO.getCollectionsWithDetails(accountId);
        if(details == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }
}
