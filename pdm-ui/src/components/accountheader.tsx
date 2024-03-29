import { Account } from '../props/props'
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

export const AccountHeader = ({account} : {account: Account}) => {

    let navigator = useNavigate();
    useEffect(() => {
        if(account === undefined) {
            navigator('/')
        }
    })

    return (
        <div className = "AccountPage">
            <h2>{account?.accountLogin!}</h2>
            <nav>
                <a href="/account"
                onClick={(e)=> {
                    e.preventDefault()
                    navigator('/account/' + account?.accountId + '/collections')
                }}
                >Collections</a>
                <a 
                href="/account"
                onClick={(e)=> {
                    e.preventDefault()
                    navigator('/account/' + account?.accountId + '/friends')
                }}
                >Friends</a>
                <a 
                href="/account"
                onClick={(e)=> {
                    e.preventDefault()
                    navigator('/account/' + account?.accountId + '/activity')
                }}
                >Activity</a>
            </nav>
        </div>
    )
}