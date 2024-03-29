import { useEffect, useState } from "react";
import { Contributor, Genre } from "../props/props";
import { getBookContributors, getBookGenres } from "../services/bookservice";
import { Contributors } from "./contributors";



export const ContributorsWrapper = ({bookId} : {bookId: string}) => {
    let initialValue: Contributor[] = [];
    let [contributors, setContributors] = useState(initialValue);
    let [fetched, setFetched] = useState(false);

    useEffect(() => {
        if(fetched) return;
        getBookContributors(bookId).then((result) => {
            setContributors(result);
            setFetched(true);
        })
    })
    return (
        <div>
            <Contributors contributors={contributors}/>
        </div>
    )
}