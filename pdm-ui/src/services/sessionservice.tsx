import { Session } from "../props/props";


export async function getUserSessions(userId: string): Promise<Session[]> {
    const respose = await fetch('http://localhost:8080/session/' + userId);
    const data = await respose.json();
    return data;
}

export async function addSession(userId: string, bookId: string, startTime: Date, endTime: Date, progress: string): Promise<boolean> {
    const requestOptions = {
        method: 'POST',
    }
    const response = await fetch("http://localhost:8080/session?accountId=" + userId + "&bookId=" + bookId + "&startTime=" + startTime.toISOString() + "&endTime=" + endTime.toISOString() + "&progress=" + progress, requestOptions);
    const data = await response.json();
    return data;
}
