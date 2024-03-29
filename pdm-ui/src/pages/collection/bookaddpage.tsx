import { useEffect, useState } from "react"
import { Book } from "../../props/props";
import { useNavigate, useParams } from "react-router-dom";
import { searchBookTitle } from "../../services/bookservice";
import { BookSearch } from "../../components/booksearch";
import { UserHeader } from "../../components/userheader";

export const BookAddPage = () => {
    let { collectionId } = useParams()
    let initialValue: Book[] = [];
    let [contents, setContents] = useState(initialValue);
    let [searchString, setSearchString] = useState("")


    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        searchBookTitle(searchString).then((results) => {
            console.log(results);
            if(results.length >= 0) {
                setContents(results);
            }
        });
    }

    return(
        <div>
            <UserHeader />
            <form onSubmit={handleSubmit}>
                <label>Search Books</label>
                <input type="text" onChange={(e) => {
                    e.preventDefault();
                    setSearchString(e.target.value);
                }}></input>
                <input type="submit"></input>
            </form>
            <div>
                {
                    contents.map((book) => {
                        return (
                            <BookSearch book={book} collectionId={collectionId!} />
                        )
                    })
                }
            </div>

        </div>

    )
}