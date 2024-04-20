import { useEffect, useState } from 'react';
import { Account } from '../props/props';
import { getFriends } from '../services/accountservice';
import { useNavigate } from 'react-router-dom';
import { removeFriend } from "../services/accountservice"
import { FriendDeleteForm } from './frienddeleteform';

export const Friends = ({userId} : {userId : string}) => {
    let initialValue: Account[] = [];
    let [friends, setFriends] = useState(initialValue)
    let [retrieved, setRetrieved] = useState(false);
    let navigator = useNavigate()
    useEffect(() => {
        if(userId === "" || userId === undefined || retrieved) return;
        getFriends(userId).then((results) => {
            setFriends(results);

        });
        setRetrieved(true);
    }, [friends]);
    
    return (

            <div>
                {
                    friends.map((friend) => {
                        return(
                            <div key={friend.accountId}>
                                <h3>{friend.accountFirstName} {friend.accountLastName}</h3>
                                <p>{friend.accountLogin}</p>
                                <p>Last Online: {friend.accountLastAccessDate.toString()}</p>
                                <p>Account Created: {friend.accountCreationDate.toString()}</p>
                                <FriendDeleteForm friend_id={friend.accountId}/>
                            </div>
                        )
                    })
                }
            </div>    

    )
}