import React, { useState } from "react";
import Header from "../header/Header";
import {
  apiUrlEntrance,
  apiUrlExit,
  basicAuthHeader,
  userData,
} from "../../services/apiUrl";

const Details = () => {
  const [transactionType, setTransactionType] = useState<string>("Entrata");
  const [month, setMonth] = useState<string>("");
  const [category, setCategory] = useState<string>("");
  const [selectedYear, setSelectedYear] = useState<number>(
    new Date().getFullYear()
  );
  const [transactions, setTransactions] = useState<any>(null);

  const generateYearOptions = (startYear: any, endYear: any) => {
    const years = [];
    for (let year = startYear; year <= endYear; year++) {
      years.push(year);
    }
    return years;
  };

  const years = generateYearOptions(2000, new Date().getFullYear());

  const fetchDetailsData = async () => {
    const apiUrl = transactionType === "Uscita" ? apiUrlExit : apiUrlEntrance;
    const queryParams = [];

    if (selectedYear) {
      queryParams.push(`year=${selectedYear}`);
    }
    if (month) {
      queryParams.push(`month=${month}`);
    }
    if (category) {
      queryParams.push(`category=${category}`);
    }

    const queryString = queryParams.join("&");

    try {
      const response = await fetch(
        `${apiUrl}/${userData.budget.id}?${queryString}`,
        {
          method: "GET",
          headers: {
            Authorization: basicAuthHeader,
            "Content-Type": "application/json",
          },
        }
      );

      if (!response.ok) {
        throw new Error("Network response was not ok");
      }

      const data = await response.json();
      setTransactions(data);

      return data;
    } catch (error) {
      console.error("Fetching user data failed.", error);
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await fetchDetailsData();
  };

  return (
    <>
      <Header />
      <div className="container mt-20">
        <form className="max-w-md mx-auto" onSubmit={handleSubmit}>
          <div className="relative z-0 w-full mb-5 group ml-1 md:ml-0">
            <label
              htmlFor="entranceOrExit"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Seleziona il tipo di transazione che vuoi visualizzare
            </label>
            <select
              id="entranceOrExit"
              className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-black focus:outline-none focus:ring-0 focus:border-black-600 peer"
              onChange={(e) => setTransactionType(e.target.value)}
            >
              <option value="Entrata">Entrata</option>
              <option value="Uscita">Uscita</option>
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
              onChange={(e) => setCategory(e.target.value)}
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
          <div className="relative z-0 w-full mb-5 group ml-1 md:ml-0">
            <label
              htmlFor="monthSelect"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Seleziona il mese che vuoi visualizzare
            </label>
            <select
              id="monthSelect"
              className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-black focus:outline-none focus:ring-0 focus:border-black-600 peer"
              onChange={(e) => setMonth(e.target.value)}
            >
              <option value="">Seleziona il mese</option>
              <option value="1">Gennaio</option>
              <option value="2">Febbraio</option>
              <option value="3">Marzo</option>
              <option value="4">Aprile</option>
              <option value="5">Maggio</option>
              <option value="6">Giugno</option>
              <option value="7">Luglio</option>
              <option value="8">Agosto</option>
              <option value="9">Settembre</option>
              <option value="10">Ottobre</option>
              <option value="11">Novembre</option>
              <option value="12">Dicembre</option>
            </select>
          </div>
          <div className="relative z-0 w-full mb-5 group ml-1 md:ml-0">
            <label
              htmlFor="yearSelect"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Seleziona l'anno che vuoi visualizzare
            </label>
            <select
              id="yearSelect"
              className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-black focus:outline-none focus:ring-0 focus:border-black-600 peer"
              onChange={(e) => setSelectedYear(parseInt(e.target.value))}
              value={selectedYear}
            >
              {years.map((year, index) => (
                <option key={index} value={year}>
                  {year}
                </option>
              ))}
            </select>
          </div>
          <button
            type="submit"
            className="text-white w-full bg-black hover:bg-gray-900 focus:ring-4 focus:outline-none focus:ring-black-300 font-medium rounded-lg text-sm sm:w-auto px-5 py-2.5 text-center dark:bg-black-600 dark:hover:bg-black-700 dark:focus:ring-black-800"
          >
            Invia
          </button>
        </form>
        <div className="max-w-3xl mx-auto flex items-center justify-center">
          <div>
            {Array.isArray(transactions) &&
              transactions.slice(0, 5).map((transaction: any) => (
                <div
                  key={transaction.id}
                  className="max-w-sm cardColor rounded-2xl overflow-hidden shadow-lg h-full mt-10 text-center sm:w-screen w-80"
                >
                  <div className="px-6 py-4 h-full flex flex-col justify-between">
                    <div className="font-bold text-xl mb-2">
                      {transaction?.description}
                    </div>
                    <p className="text-gray-700 text-base">
                      <span className="flex justify-center">
                        {transaction.transactionDate}
                      </span>
                    </p>
                    <p className="text-gray-700 text-base">
                      {transaction.transaction}€
                    </p>
                  </div>
                </div>
              ))}
          </div>
        </div>
      </div>
    </>
  );
};

export default Details;
