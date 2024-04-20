import { useEffect, useState } from "react";
import { Account } from "../../props/props";
import { GetUserContext } from "../accountcontext";
import { getFollowers } from "../../services/accountservice";

export const FollowerCount = () => {
    let user: Account | undefined = GetUserContext();
    let [fetched, setFetched] = useState(false)
    let [followers, setFollowers] = useState(0)
    
    useEffect(() => {
        if(fetched) return;
        getFollowers(user?.accountId!).then((results) => {
            setFollowers(results)
        })
        setFetched(true);
    })
    return (
        <p id="FormContent">{followers}</p>
    )
}