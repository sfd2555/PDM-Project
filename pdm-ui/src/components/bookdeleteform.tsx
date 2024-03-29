import { removeBookFromCollection } from "../services/collectionservice"

export const BookDeleteForm = ({collectionId, bookId} : {collectionId: string, bookId: string}) => {

    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        removeBookFromCollection(collectionId, bookId);
    }
    return (
        <form onSubmit={handleSubmit}>
            <label>Remove:</label>
            <input type="submit"></input>
        </form>
    )
}