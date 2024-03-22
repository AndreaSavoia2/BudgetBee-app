import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import "./Header.css";
import icon from "../../img/bee.png";

const Header = () => {
  const apiUrl: any = process.env.REACT_APP_API_URL_BUDGET;
  const username = process.env.REACT_APP_USERNAME;
  const password = process.env.REACT_APP_PASSWORD;
  const [userData, setUserData] = useState<{ budget: number } | null>(null);
  const [menuOpen, setMenuOpen] = useState<boolean>(false);
  const basicAuthHeader = "Basic " + btoa(username + ":" + password);
  const jsonString: any = localStorage.getItem("user");
  const budgetId = JSON.parse(jsonString);

  const clearLocalStorageAndRefresh = () => {
    localStorage.clear();
    window.location.reload();
  };

  const isMobileOrTablet = () => {
    return window.innerWidth <= 768;
  };

  const [isMobileOrTabletView, setIsMobileOrTabletView] = useState(isMobileOrTablet());

  useEffect(() => {
    fetch(`${apiUrl}/${budgetId.budget.id}`, {
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

  const handleMenuToggle = () => {
    setMenuOpen(!menuOpen);
  };

  const handleOptionClick = () => {
    setMenuOpen(false);
  };

  useEffect(() => {
    const handleResize = () => {
      setIsMobileOrTabletView(isMobileOrTablet());
    };

    window.addEventListener("resize", handleResize);

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  return (
    <>
      <div className="containerHeader text-center grid items-center  test rounded-br-3xl rounded-bl-3xl">
        <img
          src={icon}
          alt=""
          className="h-20 absolute mb-20 md:mb-10 right-0"
        />
        <div
          className="hamburger absolute mb-20 md:mb-10 ml-5"
          onClick={handleMenuToggle}
        >
          <div className={`line ${menuOpen ? "open" : ""}`}></div>
          <div className={`line ${menuOpen ? "open" : ""}`}></div>
          <div className={`line ${menuOpen ? "open" : ""}`}></div>
        </div>
        {menuOpen && (
          <div className="menu-options">
            <ul>
              <li onClick={handleOptionClick}>Grafico</li>
              <hr />
              <li onClick={handleOptionClick}>Categorie</li>
              <hr />
              <li onClick={clearLocalStorageAndRefresh}>Log Out</li>
            </ul>
          </div>
        )}
        <div className="text-3xl mt-20 mb-20 sm:mt-10 sm:mb-10">
          <p>€ {userData?.budget}</p>
          <p className="text-base sm:text-lg">Bilancio totale</p>
        </div>
        {isMobileOrTabletView && (
          <div className="flex justify-between mb-5 ml-5 mr-5">
            <Link to='/transaction'><button className="rounded-full font-bold bg-black w-32 h-16 text-white">
              +
            </button>
            </Link>
            <Link to='/transaction'><button className="rounded-full font-bold bg-black w-32 h-16 text-white">
              -
            </button>
            </Link>
          </div>
        )}
      </div>
    </>
  );
};

export default Header;
