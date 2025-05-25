import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/NavBar.css';

const NavBar = () => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  return (
    <nav className="navbar">
      <div className="navbar-container">
        <button className="dropdown-button" onClick={toggleDropdown}>
          Menu
        </button>
        {isOpen && (
          <div className="dropdown-menu">
            <Link to="/teacher" className="dropdown-item">
              Teacher
            </Link>
            <Link to="/students" className="dropdown-item">
              Student
            </Link>
            <Link to="/course" className="dropdown-item">
              Course
            </Link>
            <Link to="/programme" className="dropdown-item">
              Programme
            </Link>
          </div>
        )}
      </div>
    </nav>
  );
};

export default NavBar;