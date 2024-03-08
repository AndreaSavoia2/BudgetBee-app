import React from "react";
import "./Header.css";
import icon from "../../img/bee.png";

const Header = () => {
  return (
    <>
      <div className="containerHeader text-center grid items-center justify-center test rounded-br-3xl rounded-bl-3xl">
        <img src={icon} alt="" className="h-20 absolute" />
        <p className="text-3xl mt-10 mb-10">Your budget:</p>
      </div>
    </>
  );
};

export default Header;
