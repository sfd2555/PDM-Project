import './App.css';
import { Login } from './pages/login/login';
import { Register } from './pages/login/register';
import { UserPageCollections } from './pages/user/userpagecollections';
import { UserPageFriends } from './pages/user/userpagefriends';
import { UserCollectionContentsPage } from './pages/user/usercollectioncontentspage';
import { AccountPageCollections } from './pages/account/accountpagecollections';
import { Null } from './components/null';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { AccountPageFriends } from './pages/account/accountpagefriends';
import { AccountPageSessions } from './pages/account/accountpagesessions';
import { UserPageSessions } from './pages/user/userpagesessions';
import { BookPage } from './pages/book/bookpage';
import { BookAddPage } from './pages/collection/bookaddpage';

function App() {
  return (
    <div className="App">
      <h1>Totally Not Good Reads</h1>

      <BrowserRouter>
        <Routes>
            <Route index element={ <Login />}/>
            <Route path="/login" element={ <Login />}/>
            <Route path="/register" element={ <Register />}/>
            <Route path='/user' element={ <UserPageCollections />}/>
            <Route path='/user/collections' element={ <UserPageCollections />}/>
            <Route path='/user/friends' element={ <UserPageFriends />}/>
            <Route path='/user/activity' element={ <UserPageSessions />}/>
            <Route path='/user/collections/:collectionId/add' element={<BookAddPage />} />
            <Route path='/user/collections/:accountId/:collectionId' element={ <UserCollectionContentsPage />} />
            <Route path='/account/:accountId' element={ <AccountPageCollections />} />
            <Route path='/account/:accountId/collections' element={ <AccountPageCollections />} />
            <Route path='/account/:accountId/friends' element={ <AccountPageFriends />} />
            <Route path='/account/:accountId/activity' element={ <AccountPageSessions />} />
            <Route path='books/:bookId' element={ <BookPage /> } />
            <Route path='/null' element={<Null />}/>
          </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
