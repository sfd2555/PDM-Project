import { createContext, useContext } from "react";
import { Account } from "../props/props";

export let AuthenticatorContext = createContext<Account | undefined>(undefined);

export const setUserContext = (account: Account) => {
    AuthenticatorContext = createContext<Account | undefined>(account);
}

export const GetUserContext = () => {
    const user = useContext(AuthenticatorContext);
    return user;
}