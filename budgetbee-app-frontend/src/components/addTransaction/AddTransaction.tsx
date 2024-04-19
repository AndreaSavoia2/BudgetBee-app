import React, { useState } from "react";
import Header from "../header/Header";
import { Bounce, ToastContainer, toast } from "react-toastify";
import { postTransaction } from "../../services/fetchTransaction";

const AddTransaction = () => {
  const idBudget: any = localStorage.getItem("user");
  const parse: any = JSON.parse(idBudget);

  /* FORM */
  const [transactionType, setTransactionType] = useState<any>("Entrata");

  const [formData, setFormData] = useState({
    transaction: "",
    description: "",
    category: "",
    budget: { id: parse.budget.id },
  });

  /* Funzioni */

  const handleSelectChange = (
    event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = event.target;
    setFormData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleTransactionChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    let { value } = event.target;
    value = value.replace(/,/g, ".");
    const decimalCount = (value.match(/,/g) || []).length;
    if (decimalCount > 1) {
      return;
    }
    const [, decimals] = value.split(".");
    if (decimals && decimals.length > 2) {
      return;
    }
    setFormData((prevState) => ({
      ...prevState,
      transaction: value,
    }));
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    try {
      await postTransaction(formData, transactionType);
      setFormData({
        transaction: "",
        description: "",
        category: "",
        budget: { id: parse.budget.id },
      });
      window.location.href = "/";
    } catch (error) {
      console.error("Error while posting transaction:", error);
      toast.error(
        "C'è stato un errore durante l'invio della transazione. Controlla i campi e riprova.",
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
      <Header />
      <div className="container mt-20 flex justify-center items-center">
        <ToastContainer />
        <form className="max-w-md mx-auto" onSubmit={handleSubmit}>
          <div className="relative z-0 w-full mb-5 group">
            <label
              htmlFor="entranceOrExit"
              className="block mb-2 text-sm font-medium text-gray-900"
            >
              Seleziona la transazione che vuoi aggiungere
            </label>
            <select
              id="entranceOrExit"
              className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-black focus:outline-none focus:ring-0 focus:border-black-600 peer"
              onChange={(e) => setTransactionType(e.target.value)}
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
              className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-black focus:outline-none focus:ring-0 focus:border-black-600 peer"
              placeholder=" "
              required
              value={formData.transaction}
              onChange={handleTransactionChange}
            />
            <label
              htmlFor="transaction"
              className="peer-focus:font-medium absolute text-sm text-gray duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 rtl:peer-focus:translate-x-1/4 rtl:peer-focus:left-auto peer-focus:text-black-600 peer-focus:dark:text-black peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6"
            >
              Transazione
            </label>
          </div>
          <div className="relative z-0 w-full mb-5 group">
            <input
              type="text"
              name="description"
              id="description"
              className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-black focus:outline-none focus:ring-0 focus:border-black-600 peer"
              placeholder=" "
              required
              value={formData.description}
              onChange={handleSelectChange}
            />
            <label
              htmlFor="description"
              className="peer-focus:font-medium absolute text-sm text-gray duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:start-0 rtl:peer-focus:translate-x-1/4 peer-focus:text-black-600 peer-focus:dark:text-black peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6"
            >
              Descrizione
            </label>
          </div>
          <div className="relative z-0 w-full mb-5 group">
            <label
              htmlFor="category"
              className="block mb-2 text-sm font-medium text-gray-900"
            >
              Seleziona la categoria
            </label>
            <select
              id="category"
              className="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:border-gray-600 dark:focus:border-black focus:outline-none focus:ring-0 focus:border-black-600 peer"
              value={formData.category}
              onChange={(event) =>
                setFormData({ ...formData, category: event.target.value })
              }
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
                  <option value="RIMBORSI">RIMBORSI</option>
                  <option value="REGALI">REGALI</option>
                  <option value="STIPENDIO">STIPENDIO</option>
                  <option value="ALTRO">ALTRO</option>
                </>
              )}
            </select>
          </div>
          <button
            type="submit"
            className="text-white bg-black hover:bg-gray-900 focus:ring-4 focus:outline-none focus:ring-black-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-black-600 dark:hover:bg-black-700 dark:focus:ring-black-800"
          >
            Invia
          </button>
        </form>
      </div>
    </>
  );
};

export default AddTransaction;
