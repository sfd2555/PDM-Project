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
        <form id="registrationForm" onSubmit={HandleSubmit}>
            <h1>Login</h1>
            <label>First Name:</label>
            <input type="text" value = {first} onChange={(e) => setFirstName(e.target.value)}></input>
            <label>Last Name:</label>
            <input type="text" value = {last} onChange={(e) => setLastName(e.target.value)}></input>
            <label>Username:</label>
            <input type="text" value = {username} onChange={(e) => setUsername(e.target.value)} id="login"/>
            <label>Password:</label>
            <input type="password" value = {password} onChange={(e) => setPassword(e.target.value)} id="password"/>
            <label>Email:</label>
            <input type="text" value = {email} onChange={(e) => setEmail(e.target.value)}></input>
            <input type="submit" id="submit"/>
            <a href="/login">Login</a>
        </form>
    )
}