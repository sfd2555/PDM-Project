export type Account = {
    id: String
    login: String
    password: String
    firstName: String
    lastName: String
    creationDate: Date
    lastActionDate: Date
    email: String;
}

export type Audience = {
    id: String
    name: String
}

export type Book = {
    id: String
    title: String
}

export type Collection = {
    id: String
    accountId: String
    name: String
}

export type Contributor = {
    id: String
    name: String
}

export type Format = {
    id: String
    type: String
}

export type Genre = {
    id: String
    name: String
}

export type Session = {
    accountId: String
    bookId: String
    sessionStart: String
    sessionEnd: String
    sessionProgress: String
}