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
    let [progress, setProgress] = useState("")
    let [bookId, setbookId] = useState("")

    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault()
        searchBookTitle(bookTitle).then((results) => {
            console.log(results);
            setbookId(results[0].bookId)
        });
        addSession(accountId?.accountId!, bookId, startTime, endTime, progress)
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
            <label>Pages Read:</label>
            <input type="number" onChange={(e) => {
                setProgress(e.target.value.toString());
            }}></input>
            <input type="submit"></input>
        </form>
    )
}