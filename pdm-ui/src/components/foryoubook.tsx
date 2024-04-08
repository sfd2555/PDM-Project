import { useEffect, useState } from "react"
import { Book, RefinedBook } from "../props/props"
import { getRefinedBook } from "../services/bookservice"
import { Contributors } from "./contributors"

export const ForYouBook = ({book} : {book: Book}) => {
    let [fetched, setfeteched] = useState(false)
    let init: RefinedBook = {
        bookId: "",
        bookTitle: "",
        formats: [],
        contributors : [],
        genres: []
    }
    let [refinedbook, setrefinedbook] = useState(init)

    useEffect(() => {
        if(fetched) return;
        getRefinedBook(book.bookId).then((results) => {
            setrefinedbook(results)
        })
        setfeteched(true)
    })
    
    return (
        <div>
            <h4>Genres:</h4>
            {
                refinedbook.genres.map((genre) => {
                    return(
                        <label>{genre.genreName}</label>
                    )
                })
            }
            <h4>Contributors:</h4>
            {
                refinedbook.contributors.map((contributor) => {
                    return(
                        <label>{contributor.contributorName}: {contributor.contributorType}</label>
                    )
                })
            }
            <h4>Formats:</h4>
            {
                refinedbook.formats.map((format) => {
                    return(
                        <label>{format.formatType}</label>
                    )
                })
            }
        </div>
    )
}