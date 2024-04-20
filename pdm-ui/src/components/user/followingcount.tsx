import { useEffect, useState } from "react";
import { Account } from "../../props/props";
import { GetUserContext } from "../accountcontext";
import { getFollowing } from "../../services/accountservice";

export const FollowingCount = () => {
    let user: Account | undefined = GetUserContext();
    let [fetched, setFetched] = useState(false)
    let [followers, setFollowers] = useState(0)
    
    useEffect(() => {
        if(fetched) return;
        getFollowing(user?.accountId!).then((results) => {
            setFollowers(results)
        })
        setFetched(true);
    })
    return (
        <p id="FormContent">{followers}</p>
    )
}
