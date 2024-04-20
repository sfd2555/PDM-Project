import { useEffect, useState } from "react";
import { getTopFiveNewReleases, getTopTwenty } from "../../services/bookservice";
import { Book } from "../../props/props";
import { UserHeader } from "../../components/user/userheader";
import { ReccomendedHeader } from "../../components/reccomended/reccomendedheader";

export const TopFiveNewReleases = () => {

    let initialValue: Book[] = [];
    let [books, setBooks] = useState(initialValue);
    let [retrieved, setRetrieved] = useState(false);
    
    useEffect(() => {
        if(retrieved) return;
        getTopFiveNewReleases().then((results) => {
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
            <h2>Top 5 Books in the last month</h2>
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