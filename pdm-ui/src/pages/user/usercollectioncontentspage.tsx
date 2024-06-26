//import { useParams } from "next/navigation"
import { Account } from "../../props/props";
import { GetUserContext } from "../../components/accountcontext";
import { getCollection } from "../../services/collectionservice";
import { useEffect, useState } from "react";
import { CollectionContents } from "../../components/collectioncontents/collectioncontents";
import { useNavigate, useParams } from "react-router-dom";
import { UserHeader } from "../../components/user/userheader";




export const UserCollectionContentsPage = () => {
    let { collectionId, accountId } = useParams();
    let [collectionName, setCollectionName] = useState("");
    let [retrieved, setRetrieved] = useState(false);
    let navigator = useNavigate()
    useEffect(() => {
        if(!retrieved) {
            getCollection(collectionId!).then((result) => {
                setCollectionName(result.collectionName)
            })
            setRetrieved(true);
        }
    }, [collectionName]);
    
    return (
        <div>
            <UserHeader />
            <h1>{collectionName}</h1>
            <CollectionContents collectionId={collectionId!} collectionName={collectionName} accountId={accountId!}/>
        </div>

    )
}