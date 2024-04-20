import './collectionpage.css'
import { GetUserContext } from "../../components/accountcontext"
import { CollectionForm } from "../../components/collections/collectionform"
import { Collections } from "../../components/collections/collections"
import { UserHeader } from "../../components/user/userheader"
import { Account } from "../../props/props"


export const UserPageCollections = () => {
    let account: Account | undefined = GetUserContext()
    

    return (
        <div>
            <UserHeader />
            <div id="contents">
                <Collections userId={account?.accountId!}/>
                <CollectionForm />
            </div>

        </div>

    )
}