import React, { useState, useEffect } from "react";
import "./Header.css";
import icon from "../../img/bee.png";

const Header = () => {
  const apiUrl: any = process.env.REACT_APP_API_URL;
  const username = process.env.REACT_APP_USERNAME;
  const password = process.env.REACT_APP_PASSWORD;
  const [userData, setUserData] = useState<{ budget: number } | null>(null);
  const basicAuthHeader = "Basic " + btoa(username + ":" + password);

  useEffect(() => {
    fetch(apiUrl, {
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
        setUserData(data);
      })
      .catch((error) => {
        console.error("There was a problem with the fetch operation:", error);
      });
  }, []);

  return (
    <>
      <div className="containerHeader text-center grid items-center justify-center test rounded-br-3xl rounded-bl-3xl">
        <img src={icon} alt="" className="h-20 absolute" />
        <p className="text-3xl mt-20 mb-20 sm:mt-10 sm:mb-10">
        € {userData && userData.budget}{" "}
        <p className="text-base sm:text-lg">Bilancio totale</p>
        </p>
      </div>
    </>
  );
};

export default Header;
