import { useEffect, useState } from "react"
import { Book, Format, RefinedBook } from "../props/props"
import { getBookFormats } from "../services/bookservice"
import { SingleValue, ActionMeta, InputActionMeta } from "react-select"
import { addToCollection } from "../services/collectionservice"
import Select from "react-select"

export const BookSearch = ({book, collectionId} : {book: RefinedBook, collectionId:string}) => {
    let [selectedFormat, setSelectedFormat] = useState(book.formats[0].formatId)

    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault()
        console.log(selectedFormat);
        addToCollection(collectionId, book.bookId, selectedFormat)
    }

    console.log(selectedFormat)
    return(
        <div>
            <h3>{book.bookTitle}</h3>
            Genre(s): {book.genres.map(g => g.genreName).join(", ")}
            <br/>
            Publishing Date: {book.formats[0]?.releaseDate}
            <br/>
            Contributors: {book.contributors.map(g => `${g.contributorName} (${g.contributorType})`).join(", ")}
            <form onSubmit={(e) => {
                e.preventDefault(); // Prevent default form submission
                handleSubmit(e);
            }}>
                <select value={selectedFormat} onChange={(e) => {
                    setSelectedFormat(e.target.value);
                }}>
                    {book.formats.map((format) => (
                        <option key={format.formatId} value={format.formatId}>
                            {format.formatType}
                        </option>
                    ))}
                </select>
                <input type="submit" value="Submit Query"/>
            </form>
        </div>
    )
}