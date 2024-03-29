import { Session } from "../props/props";


export async function getUserSessions(userId: string): Promise<Session[]> {
    const respose = await fetch('http://localhost:8080/session/' + userId);
    const data = await respose.json();
    return data;
}

export async function addSession(userId: string, bookId: string, startTime: Date, endTime: Date, progress: number): Promise<boolean> {
    const requestOptions = {
        method: 'POST',
    }
    const response = await fetch("http://localhost:8080/session?accountId=" + userId + "&bookId=" + bookId + "&startTime=" + startTime.getTime().toString() + "&endTime=" + endTime.getTime().toString() + "&progress=" + progress.toString(), requestOptions);
    const data = await response.json();
    return data;
}
