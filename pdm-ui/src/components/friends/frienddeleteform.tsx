import './frienddeleteform.css'
import { removeFriend } from "../../services/accountservice";
import { GetUserContext } from "../accountcontext";
import { Account } from "../../props/props";


export const FriendDeleteForm = ({friend_id} : {friend_id: string}) => {
    let accountId: Account | undefined = GetUserContext()
    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        removeFriend(accountId?.accountId!, friend_id)
    }
    return (
        <form onSubmit={handleSubmit} id="deletefriendform">
                <input id="deletefriend" type="submit" value="unfriend"></input>
        </form>
    )
}