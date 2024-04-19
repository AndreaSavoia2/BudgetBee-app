import React, { useState } from "react";
import Header from "../header/Header";
import {
  apiUrlDeleteEntrance,
  apiUrlDeleteExit,
  apiUrlEntrance,
  apiUrlExit,
  basicAuthHeader,
  userData,
} from "../../services/apiUrl";
import { Bounce, ToastContainer, toast } from "react-toastify";
import { BsTrash } from "react-icons/bs";

const Details = () => {
  const [transactionType, setTransactionType] = useState<string>("Entrata");
  const [selectedMonth, setSelectedMonth] = useState<string>("");
  const [selectedCategory, setSelectedCategory] = useState<string>("");
  const [selectedYear, setSelectedYear] = useState<number | "">("");
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
    if (selectedMonth) {
      queryParams.push(`month=${selectedMonth}`);
    }
    if (selectedCategory) {
      queryParams.push(`category=${selectedCategory}`);
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
      toast.error(
        "C'è stato un errore durante l'invio della richiesta. Controlla i campi e riprova.",
        {
          position: "top-right",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "dark",
          transition: Bounce,
        }
      );
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await fetchDetailsData();
  };

  const handleDelete = async (transactionId: any) => {
    try {
      const apiUrl =
        transactionType === "Uscita" ? apiUrlDeleteExit : apiUrlDeleteEntrance;
      const response = await fetch(`${apiUrl}/${transactionId}`, {
        method: "DELETE",
        headers: {
          Authorization: basicAuthHeader,
          "Content-Type": "application/json",
        },
      });

      if (!response.ok) {
        throw new Error("Network response was not ok");
      }

      setTransactions(
        transactions.filter(
          (transaction: any) => transaction.id !== transactionId
        )
      );

      toast.success("Transazione eliminata con successo", {
        position: "top-right",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "dark",
        transition: Bounce,
      });
      setTimeout(() => {
        window.location.reload();
      }, 2000)
    } catch (error) {
      console.error("Errore durante l'eliminazione della transazione:", error);
      toast.error(
        "C'è stato un errore durante l'eliminazione della transazione. Riprova",
        {
          position: "top-right",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "dark",
          transition: Bounce,
        }
      );
    }
  };

  return (
    <>
      <ToastContainer />
      <Header />
      <div className="container mt-20 flex justify-center">
        <form className="max-w-md mx-auto" onSubmit={handleSubmit}>
          <div className="relative z-0 w-full mb-5 group ml-1 md:ml-0">
            <label
              htmlFor="entranceOrExit"
              className="block mb-2 text-sm font-medium text-gray-900"
            >
              Seleziona il tipo di transazione che vuoi visualizzare
            </label>
            <select
              id="entranceOrExit"
              className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-black focus:outline-none focus:ring-0 focus:border-black-600 peer"
              onChange={(e) => setTransactionType(e.target.value)}
            >
              <option value="Entrata">Entrata</option>
              <option value="Uscita">Uscita</option>
            </select>
          </div>
          <div className="relative z-0 w-full mb-5 group ml-1 md:ml-0">
            <label
              htmlFor="category"
              className="block mb-2 text-sm font-medium text-gray-900"
            >
              Seleziona la categoria
            </label>
            <select
              id="category"
              className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-black focus:outline-none focus:ring-0 focus:border-black-600 peer"
              onChange={(e) => setSelectedCategory(e.target.value)}
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
                  <option value="RIMBORSI">RIMBORSI</option>
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
              className="block mb-2 text-sm font-medium text-gray-900"
            >
              Seleziona il mese che vuoi visualizzare
            </label>
            <select
              id="monthSelect"
              className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-black focus:outline-none focus:ring-0 focus:border-black-600 peer"
              onChange={(e) => setSelectedMonth(e.target.value)}
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
              className="block mb-2 text-sm font-medium text-gray-900"
            >
              Seleziona l'anno che vuoi visualizzare
            </label>
            <select
              id="yearSelect"
              className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-black focus:outline-none focus:ring-0 focus:border-black-600 peer"
              onChange={(e) => setSelectedYear(parseInt(e.target.value))}
              value={selectedYear}
            >
              <option value="">Anno:</option>
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
              transactions.map((transaction: any) => (
                <div
                  key={transaction.id}
                  className="max-w-sm cardColor rounded-2xl overflow-hidden shadow-lg h-full mt-10 text-center sm:w-screen w-80 relative"
                >
                  <div className="px-6 py-4 h-full flex flex-col justify-between relative">
                    <div className="font-bold mb-2 flex">
                      {transaction?.description}
                    </div>
                    <p className="text-gray-700 text-base">
                      <span className="flex">
                        {transaction.transactionDate}
                      </span>
                    </p>
                    <p className="text-gray-700 text-base absolute right-0 top-5 mr-2 mb-2">
                      {transaction.transaction}€
                    </p>
                    <button
                      onClick={() => handleDelete(transaction.id)}
                      className="rounded-lg text-white absolute right-0 mt-10 mr-2 mb-2"
                    >
                      <BsTrash className="w-8 h-8 text-black" />
                    </button>
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
