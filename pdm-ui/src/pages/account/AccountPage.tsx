import { useParams } from "react-router-dom";
import { AccountHeader } from "../../components/accountheader"
import { Account } from "../../props/props"
import { Collections } from "../../components/collections/collections";
import { useEffect, useState } from "react";
import { getAccount } from "../../services/accountservice";


export const AccountPage = () => {
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
            <Collections userId={account?.accountId!}/>
        </div>

    )
}