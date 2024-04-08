import { useEffect, useState } from "react";
import { Genre } from "../props/props";
import { getBookGenres } from "../services/bookservice";



export const Genres = ({genres} : {genres: Genre[]}) => {
    return (
        <div>
            <h3>Genres:</h3>
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
