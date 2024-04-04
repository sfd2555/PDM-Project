import { Account } from "../../props/props"
import { Sessions } from "../../components/sessions"
import { AccountHeader } from "../../components/accountheader"
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { getAccount } from "../../services/accountservice";

export const AccountPageSessions= () => {
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
    }, [account]);
   
    return (
        <div>
            <AccountHeader account={account!} />
            <Sessions userId={account?.accountId!} username={account?.accountLogin!}/>
        </div>

    )
}