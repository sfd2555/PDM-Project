import { useEffect, useState } from "react";
import { Audience, Genre } from "../props/props";
import { getBookAudiences, getBookGenres } from "../services/bookservice";



export const Audiences = ({audiences} : {audiences: Audience[]}) => {
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