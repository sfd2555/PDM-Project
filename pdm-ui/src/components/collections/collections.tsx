import './collections.css'
import { useEffect, useState } from 'react';
import { getUserCollectionsMetadata } from '../../services/collectionservice';
import { CollectionMetadata } from '../../props/props';
import { useNavigate } from 'react-router-dom';



export const Collections = ({userId} : {userId : string}) => {
    let initialValue: CollectionMetadata[] = [];
    let [collections, setCollections] = useState(initialValue);
    let [retrieved, setRetrieved] = useState(false);
    let navigator = useNavigate();
    useEffect(() => {
        if(userId === "" || userId === undefined || retrieved) return;
        getUserCollectionsMetadata(userId).then((results) => {
            if(results.length >= 0) {
                setCollections(results.sort((c1, c2) => c1.collectionName < c2.collectionName ? -1 : 1));
            }
        });
        setRetrieved(true);
    }, [collections]);
    
    return (

            <div>
                {
                    collections.map((collection) => {
                        return(
                            <div key={collection.collectionId} id="collection">
                                <a id="collectionDetails" href='/user/collections/'
                                    onClick={(e)=> {
                                    e.preventDefault();
                                    navigator('/user/collections/'+ userId +'/' + collection.collectionId );
                                }}
                                >{collection.collectionName}</a>
                                <p id="collectionDetails">Entries: {collection.collectionEntries}</p>
                                <p id="collectionDetails">Total pages: {collection.collectionVolume}</p>
                            </div>
                        )
                    })
                }
            </div>    

    )
}