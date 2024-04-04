import { GetUserContext } from "../../components/accountcontext"
import { UserHeader } from "../../components/userheader"
import { Account } from "../../props/props"
import { Friends } from "../../components/friends"
import { FriendForm } from "../../components/friendform"


export const UserPageFriends= () => {
    let account: Account | undefined = GetUserContext()
    

    return (
        <div>
            <UserHeader />
            <Friends userId={account?.accountId!}/>
            <FriendForm accountId={account?.accountId!} />
        </div>

    )
}