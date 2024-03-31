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
    const [allContents, setAllContents] = useState<Contains[]>([]);
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
                const sortedResults = results.sort((b1, b2) => b1.bookTitle < b2.bookTitle ? -1 : 1);
                setContents(sortedResults);
                setAllContents(sortedResults); // Update allContents with the fetched and sorted results
                console.log(allContents);
            }
        });
        setRetrieved(true);
    }, [searchString]); // Make sure to include all dependencies or adjust as needed

    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        if(contents.length >= 0) {
            setContents(allContents.filter(b => {
                if (searchString === "") return true;
                switch (searchParameter) {
                    case "title":
                        return b.bookTitle.startsWith(searchString);
                    case "publisher":
                        return b.contributors.find(c => c.contributorName.startsWith(searchString) && c.contributorType === "publisher");
                    case "genre":
                        return b.genres.find(g => g.genreName.startsWith(searchString))
                }
            }).sort((b1, b2) => {
                    let n: number = 0;
                    switch(sortParameter) {
                        case "title":
                            n = b1.bookTitle < b2.bookTitle ? -1 : 1;
                            break;
                        case "publisher":
                            let b1Publisher = b1.contributors.find(c => c.contributorType === "publisher");
                            let b2Publisher = b2.contributors.find(c => c.contributorType === "publisher");
                            if (b1Publisher && b2Publisher) {
                                n = b1Publisher.contributorName < b2Publisher.contributorName ? -1 : 1;
                            } else {
                                n = 0;
                            }
                            break;
                        case "genre":
                            n = b1.genres[0].genreName < b2.genres[0].genreName ? -1 : 1;
                            break;
                        /*case "released-year":
                            const y1 = parseInt(b1.formats[0].releaseDate.split('-')[0], 10);
                            const y2 = parseInt(b2.formats[0].releaseDate.split('-')[0], 10);
                            n = y1 - y2;
                            break;*/
                        default:
                            n = 0;
                    }
                    return n * (sortOrder === "ascending" ? 1 : -1);
                })
            )
        }
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
            <select onChange={(e) => setSearchParameter(e.target.value)}>
                <option value="title">Title</option>
                <option value="author">Author</option>
                <option value="release-date">Release Date</option>
                <option value="publisher">Publisher</option>
                <option value="genre">Genre</option>
            </select>
            <form onSubmit={handleSubmit}>
                <input type="text" value={searchString} onChange={(e) => setSearchString(e.target.value)}></input>
                Sort By: <select onChange={(e) => {
                // Update the sortParameter state with the selected option's value
                setSortParameter(e.target.value);
            }}>
                <option value="title">Book Name</option>
                <option value="publisher">Publisher</option>
                <option value="genre">Genre</option>
                <option value="released-year">Released Year</option>
            </select><select onChange={(e) => setSortOrder(e.target.value)}>
                <option value="ascending">Ascending</option>
                <option value="descending">Descending</option>
            </select>
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