import { Collection, CollectionMetadata, Contains } from "../props/props";

export async function createCollection(userId: string, collectionName: string): Promise<boolean> {
    const requestOptions = {
        method: 'POST',
    }
    const response = await fetch('http://localhost:8080/collection/create/' + userId + "?collectionName=" + collectionName, requestOptions);
    const data = await response.json();
    return data;
}

export async function addToCollection(collectionId: string, bookId: string, formatId: string): Promise<boolean> {
    const requestOptions = {
        method: 'POST',
    }
    const response = await fetch('http://localhost:8080/collection/addBook?collectionId=' + collectionId + '&bookId=' + bookId + '&formatId=' + formatId, requestOptions);
    const data = await response.json();
    return data;
}

//export async function collectionAddBook(collectionId: string, bookId)

export async function getUserCollections(userId: string): Promise<Collection[]> {
    const respose = await fetch('http://localhost:8080/collection/account/' + userId);
    const data = await respose.json();
    return data;
}

export async function getUserCollectionsMetadata(userId: string): Promise<CollectionMetadata[]> {
    const respose = await fetch('http://localhost:8080/collection/metadata/' + userId);
    const data = await respose.json();
    return data;
}

export async function getCollection(collectionId: string): Promise<Collection> {
    const respose = await fetch('http://localhost:8080/collection/' + collectionId);
    const data = await respose.json();
    return data;
}

export async function searchCollectionContents(collectionId: string, searchString: string ): Promise<Contains[]> {
    //console.log(searchString);
    //console.log('http://localhost:8080/collection/contents/' + collectionId +'?=' + searchString);
    const respose = await fetch('http://localhost:8080/collection/contents/' + collectionId + '?str=' + searchString);
    const data = await respose.json();
    return data;
}


export async function removeBookFromCollection(collectionId: string, bookId: string): Promise<boolean> {
    const requestOptions = {
        method: 'DELETE',
    }
    const response = await fetch('http://localhost:8080/collection/removeBook?collectionId=' + collectionId + '&bookId=' + bookId, requestOptions);
    const data = await response.json();
    return data;

}

export async function updateCollectionName(collectionId: string, newName: string): Promise<boolean> {
    const requestOptions = {
        method: 'PUT',
    }
    const response = await fetch('http://localhost:8080/collection/updateName?collectionId=' + collectionId + '&newName=' + newName, requestOptions);
    const data = await response.json();
    return data;

}

export async function removeCollection(collectionId: string): Promise<boolean> {
    const requestOptions = {
        method: 'DELETE',
    }
    const response = await fetch('http://localhost:8080/collection/' + collectionId, requestOptions);
    const data = await response.json();
    return data;

}