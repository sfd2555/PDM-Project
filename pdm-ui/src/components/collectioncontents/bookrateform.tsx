import { useState } from "react"
import { rateBook } from "../../services/bookservice";

export const BookRateForm = ({accountId, bookId} : {accountId: string, bookId: string}) => {
    let [rating, setRating] = useState(0)

    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        rateBook(accountId, bookId, rating);
    }

    return (
        <div id="Form">
            <form id="FormContent" onSubmit={handleSubmit}>
                <h4 id="FormContent">Rate Book</h4>
                <input id="FormContent" type="number" max="5" min="0" value={rating} onChange={(e) => {
                    e.preventDefault()
                    setRating(parseInt(e.target.value));
                }}></input>
                <input id="FormContent" type="submit"></input>
            </form>
        </div>

    )
}