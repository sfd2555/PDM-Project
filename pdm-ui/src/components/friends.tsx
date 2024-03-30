import { useEffect, useState } from 'react';
import { Account } from '../props/props';
import { getFriends } from '../services/accountservice';
import { useNavigate } from 'react-router-dom';
import { removeFriend } from "../services/accountservice"

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

            <div>
                {
                    friends.map((friend) => {
                        friend_id = friend.accountId
                        return(
                            <div key={friend.accountId}>
                                <h3>{friend.accountFirstName} {friend.accountLastName}</h3>
                                <p>{friend.accountLogin}</p>
                                <p>Last Online: {friend.accountLastAccessDate.toString()}</p>
                                <p>Account Created: {friend.accountCreationDate.toString()}</p>
                                <form onSubmit={handleSubmit}>
                                <input type="submit"></input>
                                </form>
                            </div>
                        )
                    })
                }
            </div>    

    )
}