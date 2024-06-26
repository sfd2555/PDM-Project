import { Account, Book } from "../props/props";

export async function register(first: string, last: string, username: string, password: string, email: string): Promise<boolean> {
    const requestOptions = {
        method: 'POST',
    }
    const response = await fetch('http://localhost:8080/account/register?user=' + username + '&pass=' + password + '&first=' + first + '&last=' + last + '&email=' + email, requestOptions);
    const data = await response.json();
    return data;
}

export async function login(username: string, password: string): Promise<Account> {
    const respose = await fetch('http://localhost:8080/account/login?user=' + username + "&pass=" + password);
    const data = await respose.json();
    return data;
}

export async function getAccount(userId: string): Promise<Account> {
    const response = await fetch('http://localhost:8080/account/' + userId);
    const data = await response.json();
    return data;
}

export async function addFriend(userId: string, friendEmail: string): Promise<boolean> {
    const requestOptions = {
        method: 'POST',
    }
    const response = await fetch('http://localhost:8080/account/friend/' + userId + "?friendEmail=" + friendEmail, requestOptions);
    const data = await response.json();
    return data;
}

export async function removeFriend(userId: string, friendId: string): Promise<boolean> {
    const requestOptions = {
        method: 'POST',
    }
    const response = await fetch('http://localhost:8080/account/removeFriend?account_id=' + userId + "&friend_id=" + friendId, requestOptions)
    const data = await response.json();
    return data;
}

export async function getFriends(userId: string): Promise<Account[]> {
    const response = await fetch('http://localhost:8080/account/friend/' + userId);
    const data = await response.json();
    return data;
}

export async function getForYou(userId: string): Promise<Book[]> {
    const response = await fetch('http://localhost:8080/account/foryou/' + userId);
    const data = await response.json();
    return data;
}

export async function getFollowers(userId: string): Promise<number> {
    const response = await fetch('http://localhost:8080/account/followers/' + userId + '/count');
    const data = await response.json();
    return data;
}

export async function getFollowing(userId: string): Promise<number> {
    const response = await fetch('http://localhost:8080/account/following/' + userId + '/count');
    const data = await response.json();
    return data;
}