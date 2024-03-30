import { useEffect, useState } from 'react';
import { getUserCollectionsMetadata } from '../services/collectionservice';
import { CollectionMetadata } from '../props/props';
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
                setCollections(results);
            }
        });
        setRetrieved(true);
    }, [collections]);
    
    return (

            <div>
                {
                    collections.map((collection) => {
                        return(
                            <div key={collection.collectionId}>
                                <h2><a href='/user/collections/'
                                    onClick={(e)=> {
                                    e.preventDefault();
                                    navigator('/user/collections/'+ userId +'/' + collection.collectionId );
                                }}
                                >{collection.collectionName}</a></h2>
                                <p>Entries: {collection.collectionEntries}</p>
                                <p>Total pages: {collection.collectionVolume}</p>
                            </div>
                        )
                    })
                }
            </div>    

    )
}