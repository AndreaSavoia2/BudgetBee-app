// App.js
import React, { useEffect, useState } from "react";
import "./App.css";
import Home from "./components/home/Home";
import Login from "./components/login/Login";
import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import Registration from "./components/Registration/Registration";
import AddTransaction from "./components/addTransaction/AddTransaction";
import Details from "./components/details/Details";
import ProtectedComponent from './components/protectedComponents/ProtectedComponent';
import Card from "./components/card/Card";

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(() => {
    const storedAuth = localStorage.getItem("isAuthenticated");
    return storedAuth ? storedAuth === "true" : false;
  });

  const handleLoginSubmit = () => {
    setIsAuthenticated(true);
  };

  useEffect(() => {
    localStorage.setItem("isAuthenticated", isAuthenticated.toString());
  }, [isAuthenticated]);

  const handleRegistrationSubmit = () => {
    setIsAuthenticated(true);
  };

  return (
    <Router>
      <Routes>
        <Route path="/" element={isAuthenticated ? <Home /> : <Login onSubmit={handleLoginSubmit} />} />
        <Route path="/register" element={isAuthenticated ? <Navigate to="/" /> : <Registration onSubmit={handleRegistrationSubmit}/>} />
        <Route element={<ProtectedComponent />}>
          <Route path="/" element={<Home />} />
          <Route path="/card" element={<Card />} />
          <Route path="/transaction" element={<AddTransaction />} />
          <Route path="/details" element={<Details />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
