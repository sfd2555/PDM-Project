import { useState } from "react"
import { addSession } from "../services/sessionservice"

export const SessionForm = ({accountId, bookId} : {accountId: string, bookId: string}) => {
    let [startTime, setStartTime] = useState(new Date())
    let [endTime, setEndTime] = useState(new Date())
    let [progress, setProgress] = useState(0)

    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault()
        addSession(accountId, bookId, startTime, endTime, progress)
    }

    return (
        <form onSubmit={handleSubmit}>
            <label>Start: </label>
            <input type="time" value={startTime.toUTCString()} onChange={(e) => {
                e.preventDefault();
                setStartTime(startTime);
            }}></input>
            <label>End: </label>
            <input type="time" value={endTime.toUTCString()} onChange={(e) => {
                e.preventDefault();
                setEndTime(endTime);
            }}></input>
            <label>Pages Read:</label>
            <input type="number" value={progress} onChange={(e) => {
                setProgress(progress);
            }}></input>
            <input type="submit"></input>
        </form>
    )
}