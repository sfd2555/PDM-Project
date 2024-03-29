import { useEffect, useState } from 'react';
import { Session } from '../props/props';
import { getUserSessions } from '../services/sessionservice';
import { SessionForm } from './sessionform';




export const Sessions = ({userId, username} : {userId : string, username : string}) => {
    let initialValue: Session[] = [];
    let [sessions, setSessions] = useState(initialValue);
    let [retrieved, setRetrieved] = useState(false);
    
    useEffect(() => {
        if(userId === "" || userId === undefined || retrieved) return;
        getUserSessions(userId).then((results) => {
            if(results.length >= 0) {
                setSessions(results);
            }
        });
        setRetrieved(true);
    }, [sessions]);
    
    return (

            <div>
                {
                    sessions.map((session) => {

                        return(
                            <div>
                                <h3>{session.bookTitle}</h3>
                                <p>Start: {session.sessionStart.toString()} - End: {session.sessionEnd.toString()}</p>
                                <p>Pages Read: {session.sessionProgress}</p>
                                <SessionForm accountId={userId} bookId={session.bookId} />
                            </div>
                        )
                    })
                }
            </div>    

    )
}