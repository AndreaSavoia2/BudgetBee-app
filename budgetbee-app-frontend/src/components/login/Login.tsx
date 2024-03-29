import React, { useState } from "react";
import { Bounce, ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./Login.css";
import icon from "../../img/bee.png";
import { Link } from "react-router-dom";
import { loginUser } from "../../services/fetchLogin";
interface LoginFormProps {
  onSubmit: (username: string, password: string) => void;
}

const Login = ({ onSubmit }: LoginFormProps) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (event: any) => {
    event.preventDefault();
    try {
      const userData = await loginUser(username, password);
      onSubmit(username, password);
    } catch (error) {
      toast.error("Hai sbagliato la password o il nome utente. Riprova", {
        position: "top-center",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "dark",
        transition: Bounce,
      });
      console.error("There was a problem with the fetch operation:", error);
    }
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen background">
      <ToastContainer />
      <img src={icon} alt="" className="h-20" />
      <h1 className="text-4xl font-bold mb-4 text-center">
        Welcome to BudgetBee!
      </h1>
      <p className="text-lg mb-8 paragraph">Please log in to continue</p>
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
        <button
          type="submit"
          className="w-full py-2 px-4 bg-black text-white rounded-md hover:bg-gray-950 focus:outline-none focus:ring-2 focus:ring-offset-2"
        >
          LOG IN
        </button>
        <Link to="/register">
          <button
            type="submit"
            className="w-full py-2 px-4 registrationButton rounded-md focus:outline-none focus:ring-2 focus:ring-offset-2"
          >
            Non sei registrato? Clicca qui
          </button>
        </Link>
      </form>
    </div>
  );
};

export default Login;
