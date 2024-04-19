import './login.css'
import { useState } from 'react';
import { login } from '../../services/accountservice'
import { useNavigate } from 'react-router-dom';
import { setUserContext } from '../../components/accountcontext';



export const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    let navigate = useNavigate()
    
    const HandleSubmit = (event: { preventDefault: () => void; }) => {
        //console.log('button pressed')
        event.preventDefault();
        const acct = login(username, password);
        acct.then((result) => {
            //console.log('Request made')
            if(result === null) {
                //console.log('user not found')
                navigate('/login');
            }
            //console.log('user found')
            setUserContext(result);
            navigate('/user/collections');
        });
        
    }

    return (
        <div id="main">
            <form id="loginForm" onSubmit={HandleSubmit}>
                <h2 id="title">Login</h2>
                <label id="detail">Username:</label>
                <input type="text" value = {username} onChange={(e) => setUsername(e.target.value)} id="input"/>
                <label id="detail">Password:</label>
                <input type="password" value = {password} onChange={(e) => setPassword(e.target.value)} id="input"/>
                <input type="submit" id="option"/>
                <a id="option" href="/register">Register</a>
            </form>
        </div>

    )
}