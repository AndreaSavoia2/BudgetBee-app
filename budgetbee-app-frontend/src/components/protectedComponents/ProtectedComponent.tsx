import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';

const ProtectedComponent = () => {
  const isAuthenticated = localStorage.getItem("isAuthenticated");

  if (isAuthenticated === "false") {
    return <Navigate to="/" />;
  }

  return <Outlet />;
};

export default ProtectedComponent;
