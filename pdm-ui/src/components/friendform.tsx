import { useState } from "react"
import { addFriend } from "../services/accountservice"



export const FriendForm = ({accountId} : {accountId: string}) => {
    let [email, setEmail] = useState("")

    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        addFriend(accountId, email);
    }

    return (
        <form onSubmit={handleSubmit}>
            <h2>New Friend</h2>
            <label>email: </label>
            <input type="text" value={email} onChange={(e) => {
                e.preventDefault()
                setEmail(e.target.value)
            }}></input>
            <input type="submit"></input>
        </form>
    )
}