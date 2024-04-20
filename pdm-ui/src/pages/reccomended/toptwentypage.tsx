import { useEffect, useState } from "react";
import { getTopTwenty } from "../../services/bookservice";
import { Book } from "../../props/props";
import { UserHeader } from "../../components/user/userheader";
import { ReccomendedHeader } from "../../components/reccomended/reccomendedheader";

export const TopTwentyPage = () => {

    let initialValue: Book[] = [];
    let [books, setBooks] = useState(initialValue);
    let [retrieved, setRetrieved] = useState(false);
    
    useEffect(() => {
        if(retrieved) return;
        getTopTwenty().then((results) => {
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
            <h2>Top Twenty Books in the Last 90 Days</h2>
            {
                books.map((book) => {
                    return (
                        <div id="Form">
                            <h3 id="FormContent">{book.bookTitle}</h3>
                        </div>
                        
                    )
                })
            }
        </div>
    )
}