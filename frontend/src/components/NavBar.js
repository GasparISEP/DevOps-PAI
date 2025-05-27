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
          <Link to="/"><img className="logo-img" src={logoImage} alt="ISEP logo"/></Link>
        </div>

        <ul className="navbar-links-list-div">
          <li className="navbar-dropdown" onMouseEnter={handleMouseEnter} onMouseLeave={handleMouseLeave}>
            <span className="navbar-link" tabIndex={0}>
              <i className="fas fa-chalkboard-teacher"></i> Teacher
            </span>
            <ul className={`navbar-dropdown-menu${isOpen ? ' show' : ''}`}>
              <li>
                <Link to="/teachers/register" className="navbar-link" onClick={() => setIsOpen(false)}>
                  <i className="fas fa-user-plus"></i> Register Teacher
                </Link>
              </li>
              <li>
                <Link to="/teachers/display" className="navbar-link" onClick={() => setIsOpen(false)}>
                  <i className="fas fa-users"></i> Display Teachers
                </Link>
              </li>
            </ul>
          </li>
          <li>
            <Link to="/students" className="navbar-link">
              <i className="fas fa-user-graduate"></i> Student
            </Link>
          </li>
          <li>
            <Link to="/courses" className="navbar-link">
              <i className="fas fa-book-open"></i> Course
            </Link>
          </li>
          <li>
            <Link to="/programmes" className="navbar-link">
              <i className="fas fa-graduation-cap"></i> Programme
            </Link>
          </li>
        </ul>
        <div className="navbar-ghost-div"></div>
      </nav>

    </div>
  );
};

export default NavBar;

