import { useEffect, useState } from "react";
import { Genre } from "../props/props";
import { getBookGenres } from "../services/bookservice";



export const Genres = ({bookId} : {bookId: string}) => {
    let initialValue: Genre[] = [];
    let [genres, setGenres] = useState(initialValue);
    let [fetched, setFetched] = useState(false);
    useEffect(() => {
        if(fetched) return;
        getBookGenres(bookId).then((result) => {
            if(result == null) {
                setGenres(result);
                setFetched(true);
            } 
        })
    })
    return (
        <div>
            {
                genres.map((genre) => {
                    return (
                        <p>{genre.genreName}</p>
                    )
                })
            }
        </div>
    )
}
