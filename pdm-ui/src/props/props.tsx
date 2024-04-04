export interface Account {
    accountId: string;
    accountLogin: string;
    accountPassword: string;
    accountFirstName: string;
    accountLastName: string;
    accountCreationDate: Date;
    accountLastAccessDate: Date;
    accountEmail: string;
}

export interface Audience {
    audienceId: string;
    audienceName: string;
}

export interface Book {
    bookId: string;
    bookTitle: string;
}

export interface Contains {
    collectionId: string;
    bookId: string;
    bookTitle: string;
    formatType: string;
    bookLength: number;
    genres: Genre[];
    contributors: Contributor[];
}

export interface Collection {
    collectionId: string;
    accountId: string;
    collectionName: string;
}

export interface CollectionMetadata {
    collectionId: string;
    accountId: string;
    collectionName: string;
    collectionEntries: number;
    collectionVolume: number;
}

export interface Contributor {
    contributorId: string;
    contributorName: string;
    contributorType: string;
}

export interface Format {
    formatId: string;
    formatType: string;
    bookLength: number;
    releaseDate: string;
}

export interface Genre {
    genreId: string;
    genreName: string;
}

export interface Session {
    accountId: string;
    bookId: string;
    bookTitle: string
    sessionStart: Date;
    sessionEnd: Date;
    sessionProgress: string;
}

export interface RefinedBook {
    bookId: string;
    bookTitle: string;
    formats: Format[];
    contributors: Contributor[];
    genres: Genre[];
}