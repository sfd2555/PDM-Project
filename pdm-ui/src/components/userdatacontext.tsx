import { createContext, useContext } from "react";
import { Account, Collection } from "../props/props";
import { getUserCollections } from "../services/collectionservice";
import { getFriends } from "../services/accountservice";

export type UserData = {
    collections: Collection[] | undefined
    friends: Account[] | undefined
}

export let UserDataContext = createContext<UserData | undefined>(undefined);

export const updateUserDataContext = async (userId: string) => {
    let newUserData: UserData = {collections: [], friends: []};
    newUserData.collections = await getUserCollections(userId);
    newUserData.friends = await getFriends(userId);
    UserDataContext = createContext<UserData | undefined>(newUserData);
}

export const GetUserDataContext = () => {
    const userData = useContext(UserDataContext);
    return userData;
}