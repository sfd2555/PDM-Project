import { useEffect, useState } from "react"
import { Book, RefinedBook } from "../props/props"
import { Audiences } from "./audiences"
import { ContributorsWrapper } from "./contributorswrapper"
import { Formats } from "./formats"
import { Genres } from "./genres"
import { getBook, getRefinedBook } from "../services/bookservice"

export const BookComponent = ({bookId} : {bookId: string}) => {
    let initialBook: RefinedBook = {
        bookId: "",
        bookTitle: "",
        formats: [],
        contributors: [],
        genres: []
    }
    let[ book, setBook ] = useState(initialBook)
    let[ fetched, setFetched] = useState(false);
    useEffect(() => {
        if(fetched) return;
        getRefinedBook(bookId).then((result) => {
            setBook(result)
        })
        setFetched(true)
    })

    return (
        <div key={bookId}>
            <h2>{book.bookTitle}</h2>
            <Genres genres={book.genres}/>
            <ContributorsWrapper contributors={book.contributors}/>
            <Formats formats={book.formats} />
        </div>
    )   
}