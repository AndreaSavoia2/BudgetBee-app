import React, { useEffect, useState } from 'react';
import './App.css';
import Home from './components/home/Home';
import Login from './components/login/Login';

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(() => {
    const storedAuth = localStorage.getItem('isAuthenticated');
    return storedAuth ? storedAuth === 'true' : false;
  });

  // Update localStorage on authentication state change
  useEffect(() => {
    localStorage.setItem('isAuthenticated', isAuthenticated.toString());
  }, [isAuthenticated]);
  const handleLoginSubmit = () => {
    setIsAuthenticated(true);
  };

  return (
    <>
    {isAuthenticated ? <Home /> : <Login onSubmit={handleLoginSubmit} />}
    </>
  );
}

export default App;
