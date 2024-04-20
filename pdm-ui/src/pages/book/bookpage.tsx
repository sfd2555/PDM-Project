import { useParams } from "react-router-dom"
import { Audiences } from "../../components/audiences"
import { ContributorsWrapper } from "../../components/contributorswrapper"
import { Formats } from "../../components/formats"
import { Genres } from "../../components/genres"
import { Book } from "../../props/props"
import { useEffect, useState } from "react"
import { getBook } from "../../services/bookservice"
import { BookComponent } from "../../components/bookcomponent"
import { UserHeader } from "../../components/user/userheader"


export const BookPage = () => {
    let { bookId } = useParams();
    return (
        <div key={bookId}>
            <UserHeader />
            <BookComponent bookId={bookId!} />
        </div>
    )
}