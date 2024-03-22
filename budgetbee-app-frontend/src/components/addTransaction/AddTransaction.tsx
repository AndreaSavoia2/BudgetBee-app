import React from "react";
import Header from "../header/Header";

const AddTransaction = () => {
  return (
    <>
      <Header />
      <div className="container mt-20 items-center">
        <form className="max-w-md mx-auto">
        <div className="relative z-0 w-full mb-5 group">
            <label
              htmlFor="entranceOrExit"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Seleziona la transazione che vuoi aggiungere
            </label>
            <select
              id="entranceOrExit"
              className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-black focus:outline-none focus:ring-0 focus:border-black-600 peer"
            >
              <option>Entrata</option>
              <option>Uscita</option>
            </select>
          </div>
          <div className="relative z-0 w-full mb-5 group">
            <input
              type="text"
              name="transaction"
              id="transaction"
              className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-black focus:outline-none focus:ring-0 focus:border-black-600 peer"
              placeholder=" "
              required
            />
            <label
              htmlFor="transaction"
              className="peer-focus:font-medium absolute text-sm text-gray dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 rtl:peer-focus:translate-x-1/4 rtl:peer-focus:left-auto peer-focus:text-black-600 peer-focus:dark:text-black peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6"
            >
              Transazione
            </label>
          </div>
          <div className="relative z-0 w-full mb-5 group">
            <input
              type="text"
              name="description"
              id="description"
              className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-black focus:outline-none focus:ring-0 focus:border-black-600 peer"
              placeholder=" "
              required
            />
            <label
              htmlFor="description"
              className="peer-focus:font-medium absolute text-sm text-gray dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 rtl:peer-focus:translate-x-1/4 peer-focus:text-black-600 peer-focus:dark:text-black peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6"
            >
              Descrizione
            </label>
          </div>
          <div className="relative z-0 w-full mb-5 group">
            <label
              htmlFor="category"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Seleziona la categoria
            </label>
            <select
              id="category"
              className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-black focus:outline-none focus:ring-0 focus:border-black-600 peer"
            >
              <option>United States</option>
              <option>Canada</option>
              <option>France</option>
              <option>Germany</option>
            </select>
          </div>
          <button
            type="submit"
            className="text-white bg-black hover:bg-gray-900 focus:ring-4 focus:outline-none focus:ring-black-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-black-600 dark:hover:bg-black-700 dark:focus:ring-black-800"
          >
            Submit
          </button>
        </form>
      </div>
    </>
  );
};

export default AddTransaction;
