import { useEffect, useState } from "react"
import {Book, Contributor, RefinedBook} from "../../props/props";
import { useNavigate, useParams } from "react-router-dom";
import {
    getBookContributors,
    getBookFormats,
    getBookGenres,
    getRefinedBook,
    searchBookTitle, searchRefinedBookTitle
} from "../../services/bookservice";
import { BookSearch } from "../../components/booksearch";
import { UserHeader } from "../../components/userheader";

export const BookAddPage = () => {
    let { collectionId } = useParams()
    let initialValue: Book[] = [];
    let [contents, setContents] = useState(initialValue);
    let [searchString, setSearchString] = useState("")
    let [sortParameter, setSearchParameter] = useState("title")
    let [sortOrder, setSortOrder]: any = useState("ascending"); // Default to ascending


    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        searchRefinedBookTitle(searchString).then(async (results) => {
            console.log(results)
            if(results.length >= 0) {

                setContents(results.sort((b1, b2) => {
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
                        case "released-year":
                            const y1 = parseInt(b1.formats[0].releaseDate.split('-')[0], 10);
                            const y2 = parseInt(b2.formats[0].releaseDate.split('-')[0], 10);
                            n = y1 - y2;
                            break;
                        default:
                            n = 0;
                    }
                    return n * (sortOrder === "ascending" ? 1 : -1);
                }));
            }
        });
    }

    return(
        <div>
            <UserHeader />
            <form onSubmit={handleSubmit}>
                <label>Search Books</label>
                <select onChange={(e) => {
                    // This is where you would handle the change
                }}>
                    <option value="title">Title</option>
                    <option value="author">Author</option>
                    <option value="release-date">Release Date</option>
                    <option value="publisher">Publisher</option>
                    <option value="genre">Genre</option>
                </select>
                <input type="text" onChange={(e) => {
                    e.preventDefault();
                    setSearchString(e.target.value);
                }}></input>
                Sort By: <select onChange={(e) => {
                // Update the sortParameter state with the selected option's value
                setSearchParameter(e.target.value);
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
            <div>
                {
                    contents.map((book) => {
                        return (
                            <BookSearch book={book} collectionId={collectionId!}/>
                        )
                    })
                }
            </div>

        </div>

    )
}