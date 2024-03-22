import React, { useEffect, useState } from "react";
import "./App.css";
import Home from "./components/home/Home";
import Login from "./components/login/Login";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Registration from "./components/Registration/Registration";
import AddTransaction from "./components/addTransaction/AddTransaction";

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
        <Route
          path="/"
          element={isAuthenticated ? <Home /> : <Login onSubmit={handleLoginSubmit} />}
        />
        <Route
          path="/register"
          element={<Registration onSubmit={handleRegistrationSubmit} />}
        />
        <Route path="/transaction" element={<AddTransaction />} />
      </Routes>
    </Router>
  );
}

export default App;
