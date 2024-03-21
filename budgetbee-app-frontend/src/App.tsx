import React, { useEffect, useState } from "react";
import "./App.css";
import Home from "./components/home/Home";
import Login from "./components/login/Login";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Registration from "./components/Registration/Registration";

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

  // Funzione che restituisce il componente corretto in base allo stato di autenticazione
  const renderCorrectComponent = () => { // Imposta il tipo di ritorno a ReactNode
    if (isAuthenticated) {
      return <Home />;
    } else {
      return <Login onSubmit={handleLoginSubmit} />;
    }
  };

  return (
    <Router>
      <Routes>
        {/* Passa la funzione 'renderCorrectComponent' come attributo 'element' */}
        <Route path="/" element={renderCorrectComponent()} />
        <Route path="/register" element={<Registration onSubmit={handleRegistrationSubmit} />} />
      </Routes>
    </Router>
  );
}

export default App;
