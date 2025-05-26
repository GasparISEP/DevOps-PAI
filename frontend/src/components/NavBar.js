import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/NavBar.css';
import logoImage from "../assets/images/ISEP_logo.png";

const NavBar = () => {
  const [isOpen, setIsOpen] = useState(false);

  const handleMouseEnter = () => setIsOpen(true);
  const handleMouseLeave = () => setIsOpen(false);

  return (
    <div className="main-navbar-div">
      <nav className="navbar">
        <div className="logo-div">
          <img className="logo-img" src={logoImage} alt="ISEP logo"/>
        </div>
        <ul className="navbar-links-list-div">
          <li className="navbar-dropdown" onMouseEnter={handleMouseEnter} onMouseLeave={handleMouseLeave}>
            <span className="navbar-link" tabIndex={0}>
              Teacher
            </span>
            <ul className={`navbar-dropdown-menu${isOpen ? ' show' : ''}`}>
              <li>
                <Link to="/teachers/register" className="navbar-link" onClick={() => setIsOpen(false)}>
                  Register Teacher
                </Link>
              </li>
              <li>
                <Link to="/teachers/all" className="navbar-link" onClick={() => setIsOpen(false)}>
                  Display Teachers
                </Link>
              </li>
            </ul>
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

