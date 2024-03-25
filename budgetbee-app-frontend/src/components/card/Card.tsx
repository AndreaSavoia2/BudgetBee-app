import React, { useEffect, useState } from "react";
import "./Card.css";
import { Link } from "react-router-dom";

const Card = () => {
  const apiUrlExit: any = process.env.REACT_APP_API_URL_EXIT;
  const username = process.env.REACT_APP_USERNAME;
  const password = process.env.REACT_APP_PASSWORD;
  const [transactions, setTransactions] = useState<any>(null);
  const basicAuthHeader = "Basic " + btoa(username + ":" + password);
  const jsonString: any = localStorage.getItem("user");
  const budgetId = JSON.parse(jsonString);

  useEffect(() => {
    fetch(`${apiUrlExit}/${budgetId.budget.id}`, {
      method: "GET",
      headers: {
        Authorization: basicAuthHeader,
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        setTransactions(data);
      })
      .catch((error) => {
        console.error("There was a problem with the fetch operation:", error);
      });
  }, []);

  const isDesktop = () => {
    return window.innerWidth > 768;
  };

  const [isDesktopView, setIsDesktopView] = useState(isDesktop());

  useEffect(() => {
    const handleResize = () => {
      setIsDesktopView(isDesktop());
    };

    window.addEventListener("resize", handleResize);

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  return (
    <>
      <div className="flex justify-between sm:mt-20 mt-10">
        <p className="ml-10 transactions">TRANSAZIONI</p>
        <button className="mr-10">DETTAGLI</button>
      </div>
      <div className="max-w-3xl mx-auto flex items-center justify-center">
        <div>
          {isDesktopView && (
            <div className="grid justify-end mt-32 mr-5 right-0 fixed">
              <Link to="/transaction">
                <button className="rounded-full mb-3 bg-black text-white w-16 h-16">
                  +
                </button>
              </Link>
              <Link to="/transaction">
                <button className="rounded-full mb-3 bg-black text-white w-16 h-16">
                  -
                </button>
              </Link>
            </div>
          )}
          {transactions !== null &&
            transactions.slice(0, 5).map((transaction: any) => (
              <div
                key={transaction.id}
                className="max-w-sm cardColor rounded-2xl overflow-hidden shadow-lg h-full mt-10 mb-10 text-center sm:w-screen w-80"
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
    </>
  );
};

export default Card;
