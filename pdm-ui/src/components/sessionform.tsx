import { useEffect, useState } from "react"
import { addSession } from "../services/sessionservice"
import { Account, CollectionMetadata } from "../props/props"
import { GetUserContext } from "./accountcontext"
import { getBook, searchBookTitle } from "../services/bookservice"
import { getCollection, getUserCollectionsMetadata, searchCollectionContents } from '../services/collectionservice';


export const SessionForm = () => {
    let accountId: Account | undefined = GetUserContext()
    let [bookTitle, setbookTitle] = useState("")
    let [startTime, setStartTime] = useState(new Date())
    let [endTime, setEndTime] = useState(new Date())
    let [startPage, setstartPage] = useState(0)
    let [endPage, setendPage] = useState(0)
    let [bookId, setbookId] = useState("")
    let initialValue: CollectionMetadata[] = [];
    let [collections, setcollections] = useState(initialValue)
    let [retrieved, setRetrieved] = useState(false);
    let [selectedcollection, setselectedcollection] = useState("")


    useEffect(() => {
        if(accountId?.accountId === "" || accountId?.accountId === undefined || retrieved) return;
        getUserCollectionsMetadata(accountId?.accountId).then((results) => {
            if(results.length >= 0) {
                setcollections(results);
            }
        });
        setRetrieved(true)
    }, [collections]);

    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault()
        if(bookId == "") {
        searchBookTitle(bookTitle).then((results) => {
            console.log(results);
            setbookId(results[0].bookId)
        }); 
        }
        addSession(accountId?.accountId!, bookId, startTime, endTime, (endPage-startPage).toString())
    }

    const randomBook = (event: { preventDefault: () => void; }) => {
        event.preventDefault()
        if(selectedcollection == "" ) {
            setselectedcollection(collections[0].collectionId)
        }
        searchCollectionContents(selectedcollection, "").then((results) => {
            console.log(results)
            let ids: string[] = []
            for(let i=0; i<results.length-1; i++) {
                ids.push(results[i].bookId)
            }
            setbookId(ids[Math.round(Math.random()*(ids.length-1))])
        })
        getBook(bookId).then((result) => {
            console.log(result);
            setbookTitle(result.bookTitle)
        }); 
    }

    return (
        <form onSubmit={handleSubmit}>
            <h3>Create Session</h3>
            <label>Book Title: {bookTitle}</label>
            <input type="text" onChange={(e) => {
                e.preventDefault();
                setbookTitle(e.target.value);
            }}></input>
            <button onClick={randomBook}>Random Book</button>
            <select onChange={(e) => setselectedcollection(e.target.value)}>
                {collections.map((collection) => {
                        return (
                            <option value={collection.collectionId}>{collection.collectionName}</option>
                        )
                    })
                }
            </select>
            <label>Start: </label>
            <input type="datetime-local" onChange={(e) => {
                e.preventDefault();
                setStartTime(new Date(e.target.value));
            }}></input>
            <label>End: </label>
            <input type="datetime-local" onChange={(e) => {
                e.preventDefault();
                setEndTime(new Date(e.target.value));
            }}></input>
            <label>Starting Page:</label>
            <input type="number" onChange={(e) => {
                setstartPage(+e.target.value);
            }}></input>
            <label>Ending Page:</label>
            <input type="number" onChange={(e) => {
                setendPage(+e.target.value);
            }}></input>
            <input type="submit"></input>
        </form>
    )
}