import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/NavBar.css';
import logoImage from "../assets/images/ISEP_logo.png";

const NavBar = () => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  return (

      <div className="main-navbar-div">
        <nav className="navbar">

          <div className="logo-div">
            <img className="logo-img" src={logoImage} alt="ISEP logo"/>
          </div>

          <ul className="navbar-links-list-div">
            <li>
              <Link to="/teachers" className="navbar-link">
                Teacher
              </Link>
            </li>

            <li>
              <Link to="/students" className="navbar-link">
                Student
              </Link>
            </li>

            <li>
              <Link to="/courses" className="navbar-link">
                Course
              </Link>
            </li>

            <li>
              <Link to="/programmes" className="navbar-link">
                Programme
              </Link>
            </li>
          </ul>

          <div className="navbar-ghost-div"></div>
        </nav>
      </div>

  );
};

export default NavBar;

