import { GetUserContext } from "../../components/accountcontext"
import { CollectionCount } from "../../components/user/collectioncount";
import { FollowerCount } from "../../components/user/followercount";
import { FollowingCount } from "../../components/user/followingcount";
import { UserHeader } from "../../components/user/userheader";

export const UserProfile = () => {
    return (
        <div>
            <UserHeader></UserHeader>
            <div id="Form">
                <span id="FormContent"><p>Number of Collections: </p><CollectionCount></CollectionCount></span>
                <span id="FormContent"><p>Followers: </p><FollowerCount></FollowerCount></span>
                <span id="FormContent"><p>Following </p><FollowingCount></FollowingCount></span>
            </div>
        </div>


    )
}