import { removeBookFromCollection } from "../services/collectionservice"

export const BookDeleteForm = ({collectionId, bookId} : {collectionId: string, bookId: string}) => {

    const handleSubmit = (event: { preventDefault: () => void; }) => {
        event.preventDefault();
        removeBookFromCollection(collectionId, bookId);
    }
    return (
        <form onSubmit={handleSubmit}>
            <input value="Remove book" type="submit"></input>
        </form>
    )
}