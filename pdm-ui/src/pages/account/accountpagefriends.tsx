import { useParams } from "react-router-dom";
import { AccountHeader } from "../../components/accountheader"
import { Account } from "../../props/props"
import { useEffect, useState } from "react";
import { getAccount } from "../../services/accountservice";
import { Friends } from "../../components/friends";

export const AccountPageFriends = () => {
    let { accountId } = useParams();
    if(accountId === undefined) {
        accountId = "";
    }
    let initialValue: Account = {    
        accountId: "",
        accountLogin: "",
        accountPassword: "",
        accountFirstName: "",
        accountLastName: "",
        accountCreationDate: Date.prototype,
        accountLastAccessDate: Date.prototype,
        accountEmail: ""}
    let [account, setAccount] = useState(initialValue);
    let [retrieved, setRetrieved] = useState(false);
    useEffect(() => {
        if(!retrieved) {
            getAccount(accountId!).then((result) => {
                setAccount(result);
            })
            setRetrieved(true);
        }
    });

    return (
        <div>
            <AccountHeader account = {account}/>
            <Friends userId={account?.accountId!}/>
        </div>

    )
}