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

  const renderCorrectComponent = () => {
    if (isAuthenticated) {
      return <Home />;
    } else {
      return <Login onSubmit={handleLoginSubmit} />;
    }
  };

  return (
    <Router>
      <Routes>
        <Route path="/" element={renderCorrectComponent()} />
        <Route
          path="/register"
          element={<Registration onSubmit={handleRegistrationSubmit} />}
        />
      </Routes>
    </Router>
  );
}

export default App;
