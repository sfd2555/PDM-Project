import './friends.css'
import { useEffect, useState } from 'react';
import { Account } from '../../props/props';
import { getFriends } from '../../services/accountservice';
import { useNavigate } from 'react-router-dom';
import { removeFriend } from "../../services/accountservice"
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

    let navigate = useNavigate()

    let friend_id: any
    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        removeFriend(userId, friend_id)
    }
    
    return (

            <div id="friendList">
                {
                    friends.map((friend) => {
                        friend_id = friend.accountId
                        return(
                            <div key={friend.accountId} id="friend">
                                <h4 id="name">{friend.accountFirstName} {friend.accountLastName}</h4>
                                <h5 id="username">{friend.accountLogin}</h5>
                                <p id='line'>Last Online: {friend.accountLastAccessDate.toString()}</p>
                                <p id='line'>Account Created: {friend.accountCreationDate.toString()}</p>
                                <FriendDeleteForm friend_id={friend.accountId}/>
                            </div>
                        )
                    })
                }
            </div>    

    )
}