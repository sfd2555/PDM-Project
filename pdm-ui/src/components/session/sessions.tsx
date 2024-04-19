import './sessions.css'
import { useEffect, useState } from 'react';
import { Session } from '../../props/props';
import { getUserSessions } from '../../services/sessionservice';
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
                <SessionForm/>
                {
                    sessions.map((session) => {
                        return(
                            <div id="session">
                                <h3 id="sessionContents">{session.bookTitle}</h3>
                                <p id="sessionContents">Start: {new Date(session.sessionStart).toUTCString()} - End: {new Date(session.sessionEnd).toUTCString()}</p>
                                <p id="sessionContents">Pages Read: {session.sessionProgress}</p>
                            </div>
                        )
                    })
                }
            </div>    

    )
}