import { useEffect, useState } from "react";
import { Account, Book, RefinedBook } from "../props/props";
import { GetUserContext } from "./accountcontext";
import { getForYou } from "../services/accountservice";
import { collapseTextChangeRangesAcrossMultipleVersions } from "typescript";
import { getRefinedBook } from "../services/bookservice";

export const ForYou = () => {
    let account: Account | undefined = GetUserContext();
    let [fetched, setFetched] = useState(false)
    let initialValue: Book[] = []
    let [books, setbooks] = useState(initialValue)
    let initVal: RefinedBook[] = []
    let [refbooks, setrefbooks] = useState(initVal)
    
    useEffect(() => {
        if(fetched) return;
            getForYou(account?.accountId!).then((results) => {
                setbooks(results)
            })
            let temp: RefinedBook[] = []
            for(let i = 0; i < books.length; i++) {
                getRefinedBook(books[i].bookId).then((results) => {
                    temp.push(results)
                })
            }
            setrefbooks(temp);
            setFetched(true);
        })
    
    
    return (
        <div>
            {
                books.map((book) => {
                    return (
                        <div>
                            <h3>{book.bookTitle}</h3>
                        </div>
                    )
                })
            }
        </div>
    )
}