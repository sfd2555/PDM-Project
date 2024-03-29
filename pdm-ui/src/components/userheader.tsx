import { Account } from '../props/props'
import { useEffect } from 'react';
import { GetUserContext, } from './accountcontext';
import { useNavigate } from 'react-router-dom';


export const UserHeader = () => {
    let account: Account | undefined = GetUserContext();
    let navigator = useNavigate();

    useEffect(() => {
        if(account?.accountId === undefined) {
            navigator('/login');
        }
    });


    return (
        <div className = "UserPage">
            <h2>{account?.accountLogin!}</h2>
            <nav>
            <a href="/userpage/friends"
                onClick={(e)=> {
                    e.preventDefault()
                    navigator('/user/collections')
                }}
                >Collections</a>
                <a href="/userpage/friends"
                onClick={(e)=> {
                    e.preventDefault()
                    navigator('/user/friends')
                }}
                >Friends</a>
                <a href="/userpage/friends"
                onClick={(e)=> {
                    e.preventDefault()
                    navigator('/user/activity')
                }}
                >Activity</a>
            </nav>
        </div>
    )
}