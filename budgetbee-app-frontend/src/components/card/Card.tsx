import React, { useEffect, useState } from "react";
import "./Card.css";

const Card = () => {
  const apiUrlExit: any = process.env.REACT_APP_API_URL_EXIT;
  const username = process.env.REACT_APP_USERNAME;
  const password = process.env.REACT_APP_PASSWORD;
  const [transactions, setTransactions] = useState<any>(null);
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
    <>
      <div className="max-w-3xl mx-auto flex items-center justify-center">
        <div>
          {transactions !== null &&
            transactions.map((transaction: any) => (
              <div
                key={transaction.id}
                className="max-w-sm cardColor rounded overflow-hidden shadow-lg h-full mt-10 mb-10 text-center sm:w-screen w-80"
              >
                <div className="px-6 py-4 h-full flex flex-col justify-between">
                  <div>
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
              </div>
            ))}
        </div>
      </div>
    </>
  );
  
  
};

export default Card;
