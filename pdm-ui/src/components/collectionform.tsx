import { useState } from "react";
import { createCollection } from "../services/collectionservice";
import { GetUserContext } from "./accountcontext";
import { Account } from "../props/props";


export const CollectionForm = () => {
    let account: Account | undefined = GetUserContext();
    let [collectionName, setCollectionName ] = useState("");

    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        createCollection(account?.accountId!, collectionName);
    }
    return (
        <div>
            <form onSubmit={handleSubmit}>
                <h3>Create New Collection:</h3>
                <label>Collection Name:</label>
                <input type="text" value = {collectionName} onChange={(e) => setCollectionName(e.target.value)}></input>
                <input type="submit"></input>
            </form>
        </div>

    )
}