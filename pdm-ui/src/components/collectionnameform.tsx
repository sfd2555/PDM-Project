import { useState } from "react";
import { updateCollectionName } from "../services/collectionservice";
import { GetUserContext } from "./accountcontext";
import { Account } from "../props/props";


export const CollectionNameForm = ({collectionId} : {collectionId: string}) => {
    let [collectionName, setCollectionName] = useState("")

    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        updateCollectionName(collectionId, collectionName);
    }
    return (
        <div>
            <form onSubmit={handleSubmit}>
                <label>New Name:</label>
                <input type="text" value = {collectionName} onChange={(e) => setCollectionName(e.target.value)}></input>
                <input type="submit"></input>
            </form>
        </div>

    )
}