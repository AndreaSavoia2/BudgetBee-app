import React, { useState } from 'react';
import icon from "../../img/bee.png";
import { FormProps } from '../../interfaces/FormProps';
import { Navigate, Link } from 'react-router-dom';

const Registration = ({ onSubmit }: FormProps) => {
  const apiUrlRegister: any = process.env.REACT_APP_API_URL_REGISTER;
  const usernameApi = process.env.REACT_APP_USERNAME;
  const passwordApi = process.env.REACT_APP_PASSWORD;
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [registrationSuccess, setRegistrationSuccess] = useState<boolean>(false);
  const [error, setError] = useState<string>("");

  const handleSubmit = async (event: any) => {
    event.preventDefault();
    if (!username || !password) {
      setError("Username and password cannot be empty.");
      return;
    }
    try {
      const basicAuthHeader = "Basic " + btoa(usernameApi + ":" + passwordApi);
      const response = await fetch(
        `${apiUrlRegister}`,
        {
          method: "POST",
          headers: {
            Authorization: basicAuthHeader,
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ username: username, password: password })
        }
      );
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      const data = await response.json();
      onSubmit(username, password);
      localStorage.setItem("user", JSON.stringify(data));
      setRegistrationSuccess(true);
    } catch (error) {
      alert("Something went wrong. Please try again.");
      console.error("There was a problem with the fetch operation:", error);
    }
  };
  
  if (registrationSuccess) {
    return <Navigate to="/" />;
  }

  return (
    <div className="flex flex-col items-center justify-center min-h-screen background">
      <img src={icon} alt="" className="h-20" />
      <h1 className="text-4xl font-bold mb-4 text-center">
        Welcome to BudgetBee!
      </h1>
      <p className="text-lg mb-8 paragraph">Please create an account to continue</p>
      <form onSubmit={handleSubmit} className="flex flex-col space-y-4">
        <div className="flex flex-col">
          <input
            type="text"
            id="username"
            name="username"
            className="px-3 py-2 border border-gray-300 rounded-xl focus:outline-none focus:ring-1 focus:ring-blue-500"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="Username"
          />
        </div>
        <div className="flex flex-col">
          <input
            type="password"
            id="password"
            name="password"
            className="px-3 py-2 border border-gray-300 rounded-xl focus:outline-none focus:ring-1 focus:ring-blue-500"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Password"
          />
        </div>
        {error && <p className="text-red-500">{error}</p>}
        <button
          type="submit"
          className="w-full py-2 px-4 registrationButton rounded-md focus:outline-none focus:ring-2 focus:ring-offset-2"
        >
          REGISTRATION
        </button>
        <Link to="/">
          <button
            type="button"
            className="w-full py-2 px-4 bg-black text-white rounded-md hover:bg-gray-950 focus:outline-none focus:ring-2 focus:ring-offset-2"
          >
            LOG IN
          </button>
        </Link>
      </form>
    </div>
  );
};

export default Registration;
