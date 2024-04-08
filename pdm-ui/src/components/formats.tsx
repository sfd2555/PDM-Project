import { useEffect, useState } from "react";
import { Format, Genre } from "../props/props";
import { getBookFormats, getBookGenres } from "../services/bookservice";



export const Formats = ({formats} : {formats: Format[]}) => {
    return (
        <div>
            <h3>Formats:</h3>
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
