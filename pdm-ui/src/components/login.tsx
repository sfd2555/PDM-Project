import './login.css'

export const Login = () => {
    return (
        <div>
            <h1>Login</h1>
            <input type="text" id="loginfield"></input>
            <input type="password" id="loginfield"></input>
            <div id="buttons">
                <input type="button" id="login"></input>
                <input type="button" id = "register"></input>
            </div>
        </div>
    )
}