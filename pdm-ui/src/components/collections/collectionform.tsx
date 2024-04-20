import './collectionForm.css'
import { useState } from "react";
import { createCollection } from "../../services/collectionservice";
import { GetUserContext } from "../accountcontext";
import { Account } from "../../props/props";


export const CollectionForm = () => {
    let account: Account | undefined = GetUserContext();
    let [collectionName, setCollectionName ] = useState("");

    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        createCollection(account?.accountId!, collectionName);
    }
    return (
        <div id="collectionForm">
            <form onSubmit={handleSubmit}>
                <h3 id="collectionFormContent">Create New Collection:</h3>
                <label id="collectionFormContent">Collection Name:</label>
                <input id="collectionFormContent" type="text" value = {collectionName} onChange={(e) => setCollectionName(e.target.value)}></input>
                <input id="collectionFormContent" type="submit"></input>
            </form>
        </div>

    )
}