import React, { useEffect, useState } from "react";
import "./App.css";
import Home from "./components/home/Home";
import Login from "./components/login/Login";
import { createBrowserRouter, RouterProvider } from "react-router-dom";

const router = createBrowserRouter([{ path: "/", element: <Home /> }, {}]);

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

  return (
    <>{isAuthenticated ? <Home /> : <Login onSubmit={handleLoginSubmit} />}</>
  );
}

export default App;
