import { useState } from "react"
import { addSession } from "../services/sessionservice"
import { Account } from "../props/props"
import { GetUserContext } from "./accountcontext"
import { searchBookTitle } from "../services/bookservice"

export const SessionForm = () => {
    let accountId: Account | undefined = GetUserContext()
    let [bookTitle, setbookTitle] = useState("")
    let [startTime, setStartTime] = useState(new Date())
    let [endTime, setEndTime] = useState(new Date())
    let [startPage, setstartPage] = useState(0)
    let [endPage, setendPage] = useState(0)
    let [bookId, setbookId] = useState("")

    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault()
        searchBookTitle(bookTitle).then((results) => {
            console.log(results);
            setbookId(results[0].bookId)
        });
        addSession(accountId?.accountId!, bookId, startTime, endTime, (endPage-startPage).toString())
    }

    return (
        <form onSubmit={handleSubmit}>
            <h3>Create Session</h3>
            <label>Book Title: </label>
            <input type="text" onChange={(e) => {
                e.preventDefault();
                setbookTitle(e.target.value);
            }}></input>
            <label>Start: </label>
            <input type="datetime-local" onChange={(e) => {
                e.preventDefault();
                setStartTime(new Date(e.target.value));
            }}></input>
            <label>End: </label>
            <input type="datetime-local" onChange={(e) => {
                e.preventDefault();
                setEndTime(new Date(e.target.value));
            }}></input>
            <label>Starting Page:</label>
            <input type="number" onChange={(e) => {
                setstartPage(+e.target.value);
            }}></input>
            <label>Ending Page:</label>
            <input type="number" onChange={(e) => {
                setendPage(+e.target.value);
            }}></input>
            <input type="submit"></input>
        </form>
    )
}