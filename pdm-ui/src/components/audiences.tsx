import { useEffect, useState } from "react";
import { Audience, Genre } from "../props/props";
import { getBookAudiences, getBookGenres } from "../services/bookservice";



export const Audiences = ({bookId} : {bookId: string}) => {
    let initialValue: Audience[] = [];
    let [audiences, setAudiences] = useState(initialValue);
    let [fetched, setFetched] = useState(false);

    useEffect(() => {
        if(fetched) return;
        getBookAudiences(bookId).then((result) => {
            if(result != null) {
                setAudiences(result);
                setFetched(true);
            }
        })
    })
    return (
        <div>
            {
                audiences.map((audience) => {
                    return (
                        <p>{audience.audienceName}</p>
                    )
                })
            }
        </div>
    )
}