import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './components/auth_context.js';
import Home from "./components/home.js";
import QuestionWrapper from './components/home/quest/question_page_wrapper.js';
import Header from './components/header.js';
import UserProfile from './components/user_profile.js';
import SearchPage from './components/search_page.js';
import Questions from './components/home/quest/questions.js';
import Tags from './components/tags.js';
import TaggedQuestions from './components/tagged_questions.js';
import Ask from './components/ask.js';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import Divider from '@mui/material/Divider';
import Edit from './components/edit.js';
import "./App.css"

function App() {

  const style = {
    p: 0,
    width: '80%',
    border: '1px solid',
    borderColor: 'divider',
    backgroundColor: 'background.paper',
  };

  return (
    
    <AuthProvider>
        
        <Router>
        <Header>


          <div className='wrapper'>
            <div className='left'>
            <List sx={style} aria-label="mailbox folders">
              <ListItem>
                <ListItemText >
                  <a href="/home">Home</a>
                </ListItemText>
              </ListItem>
              <Divider component="li" />
              <ListItem>
                <ListItemText>
                  <a href="/questions">Questions</a>
                </ListItemText>
              </ListItem>
              <Divider component="li" />
              <ListItem>
                <ListItemText>
                  <a href='/tags'>Tags</a>
                </ListItemText>
              </ListItem>
              <Divider component="li" />
              <ListItem>
                <ListItemText>
                  <a href='/users'>Users</a>
                </ListItemText>
              </ListItem>
            </List>
            </div>

            <div className='right'>
              <Routes>
                <Route path='/' element={<Home />} />
                <Route path='*' element={<h1>Not Found</h1>} />
                <Route path='/home' element={<Home />} />
                <Route path='/questions' element={<Questions />} />
                <Route path='/question/:question_id' element={<QuestionWrapper />} />
                <Route path='/search_result' element={<SearchPage/>} />
                <Route path='/user/:username' element={<UserProfile/>} />
                <Route path='/tags' element={<Tags/>}/>
                <Route path='/:tag/questions' element={<TaggedQuestions/>}/>
                <Route path='/ask' element={<Ask/>} />
                <Route path='/users' element={<h1>Users</h1>} />
                <Route path='/edit/:question_id' element={<Edit/>} />
              </Routes>
            </div>
          </div>
          </Header>
        </Router>
        
    </AuthProvider>
  );
}

export default App;
