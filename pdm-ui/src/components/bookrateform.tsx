import { useState } from "react"
import { rateBook } from "../services/bookservice";

export const BookRateForm = ({accountId, bookId} : {accountId: string, bookId: string}) => {
    let [rating, setRating] = useState(0)

    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        rateBook(accountId, bookId, rating);
    }

    return (
        <form onSubmit={handleSubmit}>
            <h4>Rate Book</h4>
            <input type="number" max="5" min="0" value={rating} onChange={(e) => {
                e.preventDefault()
                setRating(parseInt(e.target.value));
            }}></input>
            <input type="submit"></input>
        </form>
    )
}