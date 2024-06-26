import './App.css';
import { Login } from './pages/login/login';
import { Register } from './pages/login/register';
import { UserPageCollections } from './pages/user/userpagecollections';
import { UserPageFriends } from './pages/user/userpagefriends';
import { UserCollectionContentsPage } from './pages/user/usercollectioncontentspage';
import { Null } from './components/null';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { UserPageSessions } from './pages/user/userpagesessions';
import { BookPage } from './pages/book/bookpage';
import { BookAddPage } from './pages/collection/bookaddpage';
import { AccountPage } from './pages/account/AccountPage';
import { TopTwentyPage } from './pages/reccomended/toptwentypage';
import { TopFiveNewReleases } from './pages/reccomended/topfivenewreleases';
import { TopTwentyFriendsPage } from './pages/reccomended/toptwentyfriendspage';
import { UserForYouPage } from './pages/reccomended/userforyoupage';
import { UserProfile } from './pages/user/userprofile';

function App() {
  return (
    <div className="App">
      <header>
        <h1>Totally Not Good Reads</h1>
      </header>
      <BrowserRouter>
        <Routes>
            <Route index element={ <Login />}/>
            <Route path="/login" element={ <Login />}/>
            <Route path="/register" element={ <Register />}/>
            <Route path='/user' element={ <UserProfile />}/>
            <Route path='/user/collections' element={ <UserPageCollections />}/>
            <Route path='/user/friends' element={ <UserPageFriends />}/>
            <Route path='/user/sessions' element={ <UserPageSessions />}/>
            <Route path='/user/foryou' element={ <UserForYouPage />}/>
            <Route path='user/toptwenty' element={<TopTwentyPage />}></Route>
            <Route path='user/topfivereleases' element={<TopFiveNewReleases />}></Route>
            <Route path='user/toptwentyfriends' element={<TopTwentyFriendsPage />}></Route>
            <Route path='/user/collections/:collectionId/add' element={<BookAddPage />} />
            <Route path='/user/collections/:accountId/:collectionId' element={ <UserCollectionContentsPage />} />
            <Route path='/account/:accountId' element={ <AccountPage />} />
            <Route path='books/:bookId' element={ <BookPage /> } />
            <Route path='/null' element={<Null />}/>
          </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
