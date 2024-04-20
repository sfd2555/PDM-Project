import './reccomendedheader.css'
import { useNavigate } from "react-router-dom";
import { GetUserContext } from "../accountcontext"


export const ReccomendedHeader = () => {
    let user = GetUserContext();
    let navigator = useNavigate();
    return (
        <div>
            <nav id="reccHeaderNav">
            <a id="reccNavItem" href='/user/collections/'
                onClick={(e)=> {
                e.preventDefault();
                navigator('/user/foryou');
                }}
                >For You</a>
                <a id="reccNavItem" href='/user/toptwenty'
                onClick={(e)=> {
                e.preventDefault();
                navigator('/user/toptwenty');
                }}
                >Popular</a>
                <a id="reccNavItem" href='/user/toptwentyfriends'
                onClick={(e)=> {
                e.preventDefault();
                navigator('/user/toptwentyfriends');
                }}
                >Popular Among Friends</a>
                <a id="reccNavItem" href='/user/topfivereleases'
                onClick={(e)=> {
                e.preventDefault();
                navigator('/user/topfivereleases');
                }}
                >Top 5 Releases</a>
            </nav>
        </div>
    )
}