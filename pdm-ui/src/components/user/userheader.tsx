import './userheader.css'
import { Account } from '../../props/props'
import { useEffect } from 'react';
import { GetUserContext, } from '../accountcontext';
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
        <div className = "UserPage" id="userHeader">
            <h2 id="userHeaderName">{account?.accountLogin!}</h2>
            <nav id="userHeaderNav">
            <a href="/userpage/friends" id="userNavItem"
                onClick={(e)=> {
                    e.preventDefault()
                    navigator('/user/collections')
                }}
                >Collections</a>
                <a href="/userpage/friends" id="userNavItem"
                onClick={(e)=> {
                    e.preventDefault()
                    navigator('/user/friends')
                }}
                >Friends</a>
                <a href="/userpage/friends" id="userNavItem"
                onClick={(e)=> {
                    e.preventDefault()
                    navigator('/user/sessions')
                }}
                >Sessions</a>
                <a href="/userpage/friends" id="userNavItem"
                onClick={(e)=> {
                    e.preventDefault()
                    navigator('/user/foryou')
                }}>Reccomended</a>
            </nav>
        </div>
    )
}