import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/NavBar.css';
import logoImage from "../assets/images/ISEP_logo.png";

const NavBar = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [isDropdownItemHovered, setIsDropdownItemHovered] = useState(false);

  const handleMouseEnter = () => setIsOpen(true);
  const handleMouseLeave = () => {
    setIsOpen(false);
    setIsDropdownItemHovered(false);
  };

  return (
    <div className="main-navbar-div">
      <nav className="navbar">
        <div className="logo-div">
          <Link to="/"><img className="logo-img" src={logoImage} alt="ISEP logo"/></Link>
        </div>
        <ul className="navbar-links-list-div">
          <li className="navbar-dropdown" onMouseEnter={handleMouseEnter} onMouseLeave={handleMouseLeave}>
            <span className={`navbar-link${isDropdownItemHovered ? ' teacher-dropdown-hovered' : ''}`} tabIndex={0}>
              <i className={`fas fa-chalkboard-teacher${isDropdownItemHovered ? ' teacher-icon-hovered' : ''}`}></i> Teacher
            </span>
            <ul className={`navbar-dropdown-menu${isOpen ? ' show' : ''}`}>
              <li
                onMouseEnter={() => setIsDropdownItemHovered(true)}
                onMouseLeave={() => setIsDropdownItemHovered(false)}
              >
                <Link to="/teachers/register" className="navbar-link" onClick={() => setIsOpen(false)}>
                  Register Teacher
                </Link>
              </li>
              <li
                onMouseEnter={() => setIsDropdownItemHovered(true)}
                onMouseLeave={() => setIsDropdownItemHovered(false)}
              >
                <Link to="/teachers/display" className="navbar-link" onClick={() => setIsOpen(false)}>
                  Display Teachers
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
      </nav>
    </div>
  );
};

export default NavBar;

