import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './components/auth_context.js';
import Home from "./components/home.js";
import QuestionWrapper from './components/question_page_wrapper.js';
import Header from './components/header.js';
import UserProfile from './components/user_profile.js';
import SearchPage from './components/search_page.js';

function App() {

  return (
    
    <AuthProvider>
        
        <Router>
        <Header>
          <Routes>
            <Route path='/' element={<Home />} />
            <Route path='*' element={<h1>Not Found</h1>} />
            <Route path='/home' element={<Home />} />
            <Route path='/question/:question_id' element={<QuestionWrapper />} />
            <Route path='/search_result' element={<SearchPage/>} />
            <Route path='/user/:username' element={<UserProfile/>} />
          </Routes>
          </Header>
        </Router>
        
    </AuthProvider>
  );
}

export default App;
