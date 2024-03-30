import { useEffect, useState } from "react";
import { Contains } from "../props/props";
import { removeCollection, searchCollectionContents } from "../services/collectionservice";
import { Contributors } from "./contributors";
import { useNavigate } from "react-router-dom";
import { BookDeleteForm } from "./bookdeleteform";
import { BookRateForm } from "./bookrateform";
import { CollectionNameForm } from "./collectionnameform";



export const CollectionContents = ({collectionId, collectionName, accountId} : {collectionId: string, collectionName: string, accountId: string}) => {
    let initialValue: Contains[] = [];
    let [contents, setContents] = useState(initialValue);
    let [searchString, setSearchString] = useState("")
    let [retrieved, setRetrieved] = useState(false);
    let [sortParameter, setSortParameter] = useState("title")
    let [sortOrder, setSortOrder]: any = useState("ascending"); // Default to ascending
    let [searchParameter, setSearchParameter] = useState("title")
    let navigator = useNavigate()
    

    useEffect(() => {
        if(collectionId === "" || collectionId === undefined || retrieved) return;
        searchCollectionContents(collectionId, searchString).then((results) => {
            console.log(results);
            if(results.length >= 0) {
                setContents(results.sort((b1, b2) => b1.bookTitle < b2.bookTitle ? -1 : 1));
            }
        });
        setRetrieved(true);
    }, [searchString]);

    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        searchCollectionContents(collectionId, searchString).then((results) => {
            console.log(results);
            if(results.length >= 0) {
                setContents(results);
            }
        });
    }

    const deleteSession = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        removeCollection(collectionId)
    }
    
    return (
        <div>
            <CollectionNameForm collectionId={collectionId}/>
            <br></br>
            <input type="submit" value="Delete Collection" onClick={deleteSession}></input>
            <h3><a href="/" onClick={(e) => {
                e.preventDefault()
                navigator('/user/collections/' + collectionId + '/add')
            }}>Add to Collection</a></h3>
            <br></br>
            <label>Search: </label>
            <form onSubmit={handleSubmit}>
                <input type="text" value={searchString} onChange={(e) => setSearchString(e.target.value)}></input>
                <input type="submit"></input>
            </form>
            {
                contents.map((book) => {
                    return (
                        <div key={book.bookId}>
                            <h3><a id="title" href="/" onClick={(e) => {
                                e.preventDefault()
                                navigator('/books/' + book.bookId)
                            }}>{book.bookTitle}</a></h3>
                            Pages: {book.bookLength}<br/>
                            Format: {book.formatType}<br/>
                            Genre(s): {book.genres.map(g => g.genreName).join(", ")}<br/>
                            Contributors: {book.contributors.map(g => `${g.contributorName} (${g.contributorType})`).join(", ")}<br/>
                            <BookDeleteForm collectionId={collectionId} bookId={book.bookId}/>
                            <BookRateForm accountId={accountId} bookId={book.bookId}/>
                        </div>
                    )
                })
            }

        </div>
    )


}