import { useEffect, useState } from "react";
import { Contains } from "../props/props";
import { searchCollectionContents } from "../services/collectionservice";
import { Contributors } from "./contributors";
import { useNavigate } from "react-router-dom";
import { BookDeleteForm } from "./bookdeleteform";
import { BookRateForm } from "./bookrateform";



export const CollectionContents = ({collectionId, collectionName, accountId} : {collectionId: string, collectionName: string, accountId: string}) => {
    let initialValue: Contains[] = [];
    let [contents, setContents] = useState(initialValue);
    let [searchString, setSearchString] = useState("")
    let [retrieved, setRetrieved] = useState(false);
    let navigator = useNavigate()
    

    useEffect(() => {
        if(collectionId === "" || collectionId === undefined || retrieved) return;
        searchCollectionContents(collectionId, searchString).then((results) => {
            console.log(results);
            if(results.length >= 0) {
                setContents(results);
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
    
    return (
            <div>
                <h2>{collectionName}</h2>
                <label>Search: </label>
                <form onSubmit={handleSubmit}>
                    <input type="text" value = {searchString} onChange={(e) => setSearchString(e.target.value)}></input>
                    <input type="submit"></input>
                </form>
                
                {
                    contents.map((book) => {
                        return(
                            <div key={book.bookId}>
                                <a id="title" href="/" onClick={(e)=>{

                                    e.preventDefault()
                                    navigator('/books/' + book.bookId)
                                }}>{book.bookTitle}</a>
                                <p>Pages: {book.bookLength}</p>
                                <p>Format: {book.formatType}</p>
                                <Contributors contributors={book.contributors}/>
                                <BookDeleteForm collectionId={collectionId} bookId={book.bookId}/>
                                <BookRateForm accountId={accountId} bookId={book.bookId}/>
                            </div>
                        )
                    })
                }
            </div>    
    )



}