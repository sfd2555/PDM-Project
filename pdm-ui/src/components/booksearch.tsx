import { useEffect, useState } from "react"
import { Book, Format, RefinedBook } from "../props/props"
import { getBookFormats } from "../services/bookservice"
import { SingleValue, ActionMeta, InputActionMeta } from "react-select"
import { addToCollection } from "../services/collectionservice"
import Select from "react-select"

export const BookSearch = ({book, collectionId} : {book: RefinedBook, collectionId:string}) => {
    let initialValue: Format[] = []
    let [formats, setFormat] = useState(initialValue);
    let [selectedFormat, setSelectedFormat] = useState("")
    let [fetched, setFetched] = useState(false);
    useEffect(() => {
        if(fetched) return;
        getBookFormats(book.bookId).then((results) => {
            setFormat(results)
            setFetched(true);
            if(results.length > 0) {
            }
        })

    })

    const handleSubmit = () => {
        console.log(selectedFormat);
        addToCollection(collectionId, book.bookId, selectedFormat)
    }

    return(
        <div>
            <h3>{book.bookTitle}</h3>
            <form onSubmit={handleSubmit}>
                <select value={selectedFormat} onChange={(e) => {
                    e.preventDefault();
                    setSelectedFormat(selectedFormat);
                }}>{formats.map((format) => {
                        return (
                            <option value={format.formatId}>{format.formatType}</option>
                        )
                    })}</select>
                <input type="submit" />
            </form>
        </div>
    )
}