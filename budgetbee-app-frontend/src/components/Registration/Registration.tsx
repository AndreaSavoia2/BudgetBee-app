import React, { useState } from 'react';
import { Bounce, ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import icon from "../../img/bee.png";
import { FormProps } from '../../interfaces/FormProps';
import { Navigate, Link } from 'react-router-dom';
import { checkUsernameAvailability, registerUser } from '../../services/fetchRegister';

const Registration = ({ onSubmit }: FormProps) => {
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [budget, setBudget] = useState<any>(0);
  const [registrationSuccess, setRegistrationSuccess] = useState<boolean>(false);
  const [error, setError] = useState<string>("");

  const handleSubmit = async (event: any) => {
    event.preventDefault();
    if (!username || !password) {
      toast.error("Non puoi lasciare i campi username e password vuoti.", {
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
      return;
    }
    if (budget === null || isNaN(parseFloat(budget))) {
      setError("Please enter a valid budget value.");
      return;
    }
    try {
      const usernameAvailable = await checkUsernameAvailability(username);
      if (usernameAvailable) {
        const userData = await registerUser(username, password, budget);
        onSubmit(username, password, budget);
        setRegistrationSuccess(true);
      } else {
        setError("Username già esistente, scegline uno diverso.");
      }
    } catch (error) {
      toast.error('Username già esistente, scegline uno diverso.', {
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
    }
  };
  
  if (registrationSuccess) {
    return <Navigate to="/" />;
  }
  return (
    <>
    <div className="flex flex-col items-center justify-center min-h-screen background">
    <ToastContainer
    />
      <img src={icon} alt="" className="h-20" />
      <h1 className="text-4xl font-bold mb-4 text-center">
        Benvenuto su BudgetBee!
      </h1>
      <p className="text-lg mb-8 paragraph">Crea un account per continuare</p>
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
        <div className="flex flex-col">
          <input
            type="number"
            id="budget"
            name="budget"
            className="px-3 py-2 border border-gray-300 rounded-xl focus:outline-none focus:ring-1 focus:ring-blue-500"
            value={budget + ''}
            onChange={(e) => setBudget(parseFloat(e.target.value))}
            placeholder="Set your budget"
          />
        </div>
        {error && <p className="text-red-500">{error}</p>}
        <button
          type="submit"
          className="w-full py-2 px-4 registrationButton rounded-md focus:outline-none focus:ring-2 focus:ring-offset-2"
        >
          REGISTRAZIONE
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
    </>
  );
};

export default Registration;
