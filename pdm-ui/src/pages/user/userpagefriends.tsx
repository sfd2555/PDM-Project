import './friendpage.css'
import { GetUserContext } from "../../components/accountcontext"
import { UserHeader } from "../../components/user/userheader"
import { Account } from "../../props/props"
import { Friends } from "../../components/friends/friends"
import { FriendForm } from "../../components/friends/friendform"


export const UserPageFriends= () => {
    let account: Account | undefined = GetUserContext()
    

    return (
        <div>
            <UserHeader />
            <div id="contents">
                <Friends userId={account?.accountId!}/>
                <FriendForm accountId={account?.accountId!} />
            </div>
        </div>

    )
}