import React, { useEffect, useState } from "react";
import "./Card.css";

const Card = () => {
  const apiUrlExit: any = process.env.REACT_APP_API_URL_EXIT;
  const username = process.env.REACT_APP_USERNAME;
  const password = process.env.REACT_APP_PASSWORD;
  const [transactions, setTransactions] = useState<any[]>([]);
  const basicAuthHeader = "Basic " + btoa(username + ":" + password);

  useEffect(() => {
    fetch(apiUrlExit, {
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

  return (
    <div className="max-w-3xl mx-auto">
      {transactions.map((transaction) => (
        <div
          key={transaction.id}
          className="max-w-sm w-full lg:max-w-full lg:flex"
        >
          <div className="h-48 lg:h-auto lg:w-48 flex-none bg-cover rounded-t lg:rounded-t-none lg:rounded-l text-center overflow-hidden"></div>
          <div className="cardColor border p-4 flex flex-col justify-between leading-normal rounded-2xl">
            <div className="mb-8">
              <h3 className="text-gray-900 font-bold text-xl mb-2">
                {transaction?.description}
              </h3>
              <p className="text-gray-700 text-base">
                {transaction.transactionDate}
              </p>
              <p>{transaction.transaction}</p>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
};

export default Card;
