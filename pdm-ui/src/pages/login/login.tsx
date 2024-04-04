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
        <form id="loginForm" onSubmit={HandleSubmit}>
            <h2>Login</h2>
            <label>Username:</label>
            <input type="text" value = {username} onChange={(e) => setUsername(e.target.value)} id="login"/>
            <label>Password:</label>
            <input type="password" value = {password} onChange={(e) => setPassword(e.target.value)} id="password"/>
            <input type="submit" id="submit"/>
            <a href="/register">Register</a>
        </form>
    )
}