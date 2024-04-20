import './friendform.css'
import { useState } from "react"
import { addFriend } from "../../services/accountservice"



export const FriendForm = ({accountId} : {accountId: string}) => {
    let [email, setEmail] = useState("")

    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        addFriend(accountId, email);
    }

    return (
        <div id="friendForm">
            <form onSubmit={handleSubmit}>
                <h2 id="friendFormContent">New Friend</h2>
                <label id="friendFormContent">email: </label>
                <input id="friendFormContent" type="text" value={email} onChange={(e) => {
                    e.preventDefault()
                    setEmail(e.target.value)
                }}></input>
                <input id="friendFormContent" type="submit"></input>
            </form>
        </div>

    )
}