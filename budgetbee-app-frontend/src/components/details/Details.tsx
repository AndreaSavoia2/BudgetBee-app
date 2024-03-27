import React, { useState } from "react";
import Header from "../header/Header";

const Details = () => {
    const [transactionType, setTransactionType] = useState<string>("Entrata");
    const [month, setMonth] = useState<string>("");
    const [year, setYear] = useState<string>("");
  return (
    <>
      <Header />
      <div className="container mt-20">
      <form className="max-w-md mx-auto">
          <div className="relative z-0 w-full mb-5 group ml-1 md:ml-0">
            <label
              htmlFor="entranceOrExit"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Seleziona il tipo di transazioni che vuoi visualizzare
            </label>
            <select
              id="entranceOrExit"
              className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-black focus:outline-none focus:ring-0 focus:border-black-600 peer"
              onChange={(e) => setTransactionType(e.target.value)}
            >
              <option>Entrata</option>
              <option>Uscita</option>
            </select>
          </div>
          <div className="relative z-0 w-full mb-5 group ml-1 md:ml-0">
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
              <option value="">Categoria:</option>
              {transactionType === "Uscita" && (
                <>
                  <option value="BOLLETTE">BOLLETTE</option>
                  <option value="ABBONAMENTI">ABBONAMENTI</option>
                  <option value="CARBURANTE">CARBURANTE</option>
                  <option value="BONIFICO">BONIFICO</option>
                  <option value="SPESA">SPESA</option>
                  <option value="ASSICURAZIONI">ASSICURAZIONI</option>
                  <option value="ABBONAMENTI">ABBONAMENTI</option>
                  <option value="VIAGGI">VIAGGI</option>
                  <option value="HOBBY">HOBBY</option>
                  <option value="ISTRUZIONE">ISTRUZIONE</option>
                  <option value="ABBIGLIAMENTO">ABBIGLIAMENTO</option>
                  <option value="REGALI">REGALI</option>
                  <option value="ANIMALI">ANIMALI</option>
                  <option value="ALTRO">ALTRO</option>
                </>
              )}
              {transactionType === "Entrata" && (
                <>
                  <option value="BONIFICO">BONIFICO</option>
                  <option value="RIMBORSO">RIMBORSO</option>
                  <option value="REGALI">REGALI</option>
                  <option value="STIPENDIO">STIPENDIO</option>
                  <option value="ALTRO">ALTRO</option>
                </>
              )}
            </select>
          </div>
          <div className="flex items-center mb-5 ml-1 md:ml-0">
          <input
            type="checkbox"
            id="checkbox_email"
            className="h-4 w-4 mr-2 text-blue-600 focus:ring-blue-500 border-gray-300 rounded ml-1 md:ml-0"
            // Aggiungi qui l'eventuale logica per gestire lo stato della checkbox
          />
          <label htmlFor="checkbox_email" className="text-sm text-gray-700">
            Mese
          </label>

          <input
            type="checkbox"
            id="checkbox_email"
            className="h-4 w-4 mr-2 text-blue-600 focus:ring-blue-500 border-gray-300 rounded ml-3"
            // Aggiungi qui l'eventuale logica per gestire lo stato della checkbox
          />
          <label htmlFor="checkbox_email" className="text-sm text-gray-700">
            Anno
          </label>

          <input
            type="checkbox"
            id="checkbox_email"
            className="h-4 w-4 mr-2 text-blue-600 focus:ring-blue-500 border-gray-300 rounded ml-3"
            // Aggiungi qui l'eventuale logica per gestire lo stato della checkbox
          />
          <label htmlFor="checkbox_email" className="text-sm text-gray-700">
            Categoria
          </label>
        </div>
          <button
            type="submit"
            className="text-white w-full bg-black hover:bg-gray-900 focus:ring-4 focus:outline-none focus:ring-black-300 font-medium rounded-lg text-sm sm:w-auto px-5 py-2.5 text-center dark:bg-black-600 dark:hover:bg-black-700 dark:focus:ring-black-800"
          >
            Invia
          </button>
        </form>
      </div>
    </>
  );
};

export default Details;
