import React from 'react';
import './App.css';
import Home from './components/home/Home';
import Login from './components/login/Login';

function App() {
  // const handleLoginSubmit = (username: string, password: string) => {
    
  //   console.log("Username:", username, "Password:", password); 
  // };

  return (
    <>
    <Home />
    {/* <Login onSubmit={handleLoginSubmit} /> */}
    </>
  );
}

export default App;
