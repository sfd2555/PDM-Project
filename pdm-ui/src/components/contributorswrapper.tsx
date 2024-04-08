import { useEffect, useState } from "react";
import { Contributor, Genre } from "../props/props";
import { getBookContributors, getBookGenres } from "../services/bookservice";
import { Contributors } from "./contributors";



export const ContributorsWrapper = ({contributors} : {contributors: Contributor[]}) => {
    return (
        <div>
            <h3>Contributors: </h3>
            <Contributors contributors={contributors}/>
        </div>
    )
}