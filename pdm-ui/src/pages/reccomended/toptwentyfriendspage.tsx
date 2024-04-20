import { useEffect, useState } from "react";
import { getTopTwenty, getTopTwentyFriends } from "../../services/bookservice";
import { Book } from "../../props/props";
import { UserHeader } from "../../components/userheader";
import { GetUserContext } from "../../components/accountcontext";
import { ReccomendedHeader } from "../../components/reccomendedheader";

export const TopTwentyFriendsPage = () => {
    let account = GetUserContext();
    let initialValue: Book[] = [];
    let [books, setBooks] = useState(initialValue);
    let [retrieved, setRetrieved] = useState(false);
    
    useEffect(() => {
        if(retrieved) return;
        getTopTwentyFriends(account?.accountId!).then((results) => {
            if(results.length >= 0) {
                setBooks(results);
            }
        });
        setRetrieved(true);
    }, [books]);
    return (
        <div>
            <UserHeader></UserHeader>
            <ReccomendedHeader></ReccomendedHeader>
            <h2>Top Twenty Books among Friends</h2>
            {
                books.map((book) => {
                    return (
                        <h3>{book.bookTitle}</h3>
                    )
                })
            }
        </div>
    )
}