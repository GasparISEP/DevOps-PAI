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
            <div className="dropdown-item">
              Teacher
              <div className="dropdown-submenu">
                <Link to="/teachers/register" className="dropdown-subitem">Registrar Professor</Link>
                <Link to="/teachers/all" className="dropdown-subitem">Mostrar Professores</Link>
              </div>
            </div>
            <Link to="/students" className="dropdown-item">
              Student
            </Link>
            <Link to="/courses" className="dropdown-item">
              Course
            </Link>
            <Link to="/programmes" className="dropdown-item">
              Programme
            </Link>
          </div>
        )}
      </div>
    </nav>
  );
};

export default NavBar;

