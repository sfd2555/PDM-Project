import './login.css'
import { useState } from 'react';
import { register } from '../../services/accountservice'
import { useNavigate } from 'react-router-dom';


export const Register = () => {
    const [first, setFirstName] = useState("");
    const [last, setLastName] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    let error = ""

    const HandleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        register(first, last, username, password, email);
    }

    return (
        <div id="main">
            <form id="registrationForm" onSubmit={HandleSubmit}>
                <h2 id="title">Register</h2>
                <label id="input">First Name:</label>
                <input type="text" id="input" value = {first} onChange={(e) => setFirstName(e.target.value)}></input>
                <label id="input">Last Name:</label>
                <input id="input" type="text" value = {last} onChange={(e) => setLastName(e.target.value)}></input>
                <label id="input">Username:</label>
                <input type="text" value = {username} onChange={(e) => setUsername(e.target.value)} id="option"/>
                <label id="input">Password:</label>
                <input type="password" value = {password} onChange={(e) => setPassword(e.target.value)} id="option"/>
                <label id="input">Email:</label>
                <input type="text" id="input" value = {email} onChange={(e) => setEmail(e.target.value)}></input>
                <input type="submit" id="option"/>
                <a href="/login" id="option">Login</a>
            </form>
        </div>

    )
}