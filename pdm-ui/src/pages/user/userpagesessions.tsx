import { GetUserContext } from "../../components/accountcontext"
import { UserHeader } from "../../components/userheader"
import { Account } from "../../props/props"
import { Sessions } from "../../components/sessions"

export const UserPageSessions= () => {
    let account: Account | undefined = GetUserContext()
    

    return (
        <div>
            <UserHeader />
            <Sessions userId={account?.accountId!} username={account?.accountLogin!}/>
        </div>

    )
}