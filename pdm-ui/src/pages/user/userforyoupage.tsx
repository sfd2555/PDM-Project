import { GetUserContext } from "../../components/accountcontext"
import { UserHeader } from "../../components/user/userheader"
import { Account } from "../../props/props"
import { ForYou } from "../../components/foryou"


export const UserForYouPage = () => {
    let account: Account | undefined = GetUserContext()
    

    return (
        <div>
            <UserHeader />
            <h1>For You</h1>
            <ForYou />
        </div>

    )
}