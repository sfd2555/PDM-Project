import { useEffect, useState } from "react";
import { Account } from "../../props/props";
import { GetUserContext } from "../accountcontext";
import { getNumCollections } from "../../services/collectionservice";

export const CollectionCount = () => {
    let user: Account | undefined = GetUserContext();
    let [fetched, setFetched] = useState(false)
    let [collections, setCollections] = useState(0)
    
    useEffect(() => {
        if(fetched) return;
        getNumCollections(user?.accountId!).then((results) => {
            setCollections(results)
        })
        setFetched(true);
    })
    return (
        <p>{collections}</p>
    )
}