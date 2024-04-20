import { useEffect, useState } from "react";
import { Account, Book } from "../../props/props";
import { GetUserContext } from "../accountcontext";
import { getFollowers } from "../../services/accountservice";
import { getUserTopTen } from "../../services/bookservice";
import { useNavigate } from "react-router-dom";

export const TopTen = () => {
    let user: Account | undefined = GetUserContext();
    let [fetched, setFetched] = useState(false)
    let initial: Book[] = []
    let [books, setBooks] = useState(initial)
    let navigator = useNavigate();
    useEffect(() => {
        if(fetched) return;
        getUserTopTen(user?.accountId!).then((results) => {
            setBooks(results)
        })
        setFetched(true);
    })
    return (
        <div>
            {books.map((book) => {
                return(
                    <div id="Form">
                        <h2 id="FormContent"><a href="/user/foryou"
                            onClick={(e)=> {
                            e.preventDefault()
                            navigator('/books/'+book.bookId)
                        }}
                        >{book.bookTitle}</a></h2>
                    </div>    
                )
            })}
        </div>
    )
}