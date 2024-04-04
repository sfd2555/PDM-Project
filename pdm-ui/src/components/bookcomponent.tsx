import { useEffect, useState } from "react"
import { Book } from "../props/props"
import { Audiences } from "./audiences"
import { ContributorsWrapper } from "./contributorswrapper"
import { Formats } from "./formats"
import { Genres } from "./genres"
import { getBook } from "../services/bookservice"

export const BookComponent = ({bookId} : {bookId: string}) => {
    let initialBook: Book = {
        bookId: "",
        bookTitle: ""
    }
    let[ book, setBook ] = useState(initialBook)
    let[ fetched, setFetched] = useState(false);
    useEffect(() => {
        if(fetched) return;
        getBook(bookId).then((results) => {
            setBook(results)
            setFetched(true);
        });
    })

    return (
        <div key={bookId}>
            <h3>{book.bookTitle}</h3>
            <Genres bookId={bookId!}/>
            <Audiences bookId={bookId!} />
            <ContributorsWrapper bookId={bookId!}/>
            <Formats bookId={bookId!} />
        </div>
    )   
}