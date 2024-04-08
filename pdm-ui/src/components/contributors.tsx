import { useEffect, useState } from "react";
import { Contributor } from "../props/props";

export const Contributors = ({contributors} : {contributors: Contributor[]}) => {
    let initialValue: Contributor[] = [];
    let [contributorsState, setContributorsState] = useState(initialValue);
    useEffect(() => {
        setContributorsState(contributors)
    })
    return (
        <div>
            {
                contributorsState.map((contributor) => {
                    return (
                        <p>{contributor.contributorName}: {contributor.contributorType}</p>
                    )
                })
            }
        </div>
    )
}