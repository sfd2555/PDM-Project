import { useEffect, useState } from "react"
import { Book, Format, RefinedBook } from "../props/props"
import { getBookFormats } from "../services/bookservice"
import { SingleValue, ActionMeta, InputActionMeta } from "react-select"
import { addToCollection } from "../services/collectionservice"
import Select from "react-select"

export const BookSearch = ({book, collectionId} : {book: RefinedBook, collectionId:string}) => {
    let initialValue: Format[] = []
    let [selectedFormat, setSelectedFormat] = useState("")

    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault()
        console.log(selectedFormat);
        addToCollection(collectionId, book.bookId, selectedFormat)
    }

    return(
        <div>
            <h3>{book.bookTitle}</h3>
            <label>Genres:</label>
            {book.genres.map((genre) =>{
                return (
                    <li>{genre.genreName}</li>
                )
            })}
            <br></br>
            <form onSubmit={handleSubmit}>
            <select onChange={(e) => setSelectedFormat(e.target.value)}>
                {book.formats.map((format) => {
                    return (
                        <option value={format.formatId}>{format.formatType}</option>
                    )
                })}
                </select>
            <input type="submit"></input>
            </form>
        </div>
    )
}