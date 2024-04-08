import { useEffect, useState } from "react";
import { Account, Book, RefinedBook } from "../props/props";
import { GetUserContext } from "./accountcontext";
import { getForYou } from "../services/accountservice";
import { getRefinedBook } from "../services/bookservice";
import { ForYouBook } from "./foryoubook";
import { useNavigate } from "react-router-dom";

export const ForYou = () => {
    let account: Account | undefined = GetUserContext();
    let [fetched, setFetched] = useState(false)
    let initialValue: Book[] = []
    let [books, setbooks] = useState(initialValue)
    let navigator = useNavigate();
    
    useEffect(() => {
        if(fetched) return;
        getForYou(account?.accountId!).then((results) => {
            setbooks(results)
        })
        setFetched(true);
    })
    //<ForYouBook book={book} />
    return (
        <div>
            {
                books.map((book) => {
                    return (
                        <div>
                            <h2><a href="/user/foryou"
                                onClick={(e)=> {
                                e.preventDefault()
                                navigator('/books/'+book.bookId)
                            }}
                            >{book.bookTitle}</a></h2>
                        </div>
                    )
                })
            }
        </div>
    )
}