import { GetUserContext } from "../../components/accountcontext"
import { CollectionForm } from "../../components/collectionform"
import { Collections } from "../../components/collections"
import { UserHeader } from "../../components/userheader"
import { Account } from "../../props/props"


export const UserPageCollections = () => {
    let account: Account | undefined = GetUserContext()
    

    return (
        <div>
            <UserHeader />
            <Collections userId={account?.accountId!}/>
            <CollectionForm />
        </div>

    )
}