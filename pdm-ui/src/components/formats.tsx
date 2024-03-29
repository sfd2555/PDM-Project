import { useEffect, useState } from "react";
import { Format, Genre } from "../props/props";
import { getBookFormats, getBookGenres } from "../services/bookservice";



export const Formats = ({bookId} : {bookId: string}) => {
    let initialValue: Format[] = [];
    let [formats, setFormats] = useState(initialValue);
    let [fetched, setFetched] = useState(false);
    useEffect(() => {
        if(fetched) return;
        getBookFormats(bookId).then((result) => {
            setFormats(result);
            setFetched(true);

        })
    })
    return (
        <div>
            {
                formats.map((format) => {
                    return (
                        <p>{format.formatType}</p>
                    )
                })
            }
        </div>
    )
}
