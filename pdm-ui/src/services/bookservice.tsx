import {Audience, Book, Contributor, Format, Genre, RefinedBook} from "../props/props";

export async function getBook(bookId: string): Promise<Book> {
    const respose = await fetch('http://localhost:8080/book/' + bookId);
    const data = await respose.json();
    return data;
}

export async function getRefinedBook(bookId: string): Promise<RefinedBook> {
    const respose = await fetch('http://localhost:8080/book/refined/' + bookId);
    const data = await respose.json();
    return data;
}

export async function getBookContributors(bookId: string): Promise<Contributor[]> {
    const respose = await fetch('http://localhost:8080/book/contributors/' + bookId);
    const data = await respose.json();
    return data;
}

export async function getBookGenres(bookId: string): Promise<Genre[]> {
    const respose = await fetch('http://localhost:8080/book/genres/' + bookId);
    const data = await respose.json();
    return data;
}

export async function getBookFormats(bookId: string): Promise<Format[]> {
    const respose = await fetch('http://localhost:8080/book/formats/' + bookId);
    const data = await respose.json();
    return data;
}

export async function getBookAudiences(bookId: string): Promise<Audience[]> {
    const respose = await fetch('http://localhost:8080/book/audiences/' + bookId);
    const data = await respose.json();
    return data;
}


export async function searchBookTitle(bookTitle: string): Promise<Book[]> {
    const respose = await fetch('http://localhost:8080/book/search/title?bookTitle=' + bookTitle);
    const data = await respose.json();
    return data;
}


export async function rateBook(accountId: string, bookId: string, rating: number): Promise<boolean> {
    const requestOptions = {
        method: 'POST',
    }
    const respose = await fetch("http://localhost:8080/book/rate?accountId=" + accountId + "&bookId=" + bookId + "&rating=" + rating, requestOptions);
    const data = await respose.json();
    return data;
}