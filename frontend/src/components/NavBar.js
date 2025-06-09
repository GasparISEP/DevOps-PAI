import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/NavBar.css';
import logoImage from "../assets/images/ISEP_logo.png";

const NavBar = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [isDropdownItemHovered, setIsDropdownItemHovered] = useState(false);
  const [isOpenCourseEdition, setIsOpenCourseEdition] = useState(false);
  const [isCourseEditionItemHovered, setIsCourseEditionItemHovered] = useState(false);

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

          {/* Teacher */}
          <li className="navbar-dropdown" onMouseEnter={handleMouseEnter} onMouseLeave={handleMouseLeave}>
            <span className={`navbar-link${isDropdownItemHovered ? ' teacher-dropdown-hovered' : ''}`} tabIndex={0}>
              <i className={`fas fa-chalkboard-teacher${isDropdownItemHovered ? ' teacher-icon-hovered' : ''}`}></i> Teacher
            </span>
            <ul className={`navbar-dropdown-menu${isOpen ? ' show' : ''}`}>

              {/* Register Teacher */}
              <li
                onMouseEnter={() => setIsDropdownItemHovered(true)}
                onMouseLeave={() => setIsDropdownItemHovered(false)}
              >
                <Link to="/teachers/register" className="navbar-link" onClick={() => setIsOpen(false)}>
                  Register Teacher
                </Link>
              </li>

              {/* Display Teachers */}
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

          {/* Students */}
          <li>
            <Link to="/students" className="navbar-link">
              <i className="fas fa-user-graduate"></i> Student
            </Link>
          </li>

          {/* Course */}
          <li>
            <Link to="/courses" className="navbar-link">
              <i className="fas fa-book-open"></i> Course
            </Link>
          </li>

          {/* Programme */}
          <li>
            <Link to="/programmes" className="navbar-link">
              <i className="fas fa-graduation-cap"></i> Programme
            </Link>
          </li>

          {/* Course Edition */}
          <li className="navbar-dropdown"
              onMouseEnter={() => setIsOpenCourseEdition(true)}
              onMouseLeave={() => setIsOpenCourseEdition(false)}>
            <span className={`navbar-link${isCourseEditionItemHovered ? ' courseedition-dropdown-hovered' : ''}`} tabIndex={0}>
              <i className="fas fa-calendar-alt"></i> Course Edition
            </span>
            <ul className={`navbar-dropdown-menu${isOpenCourseEdition ? ' show' : ''}`}>

              {/* Grade A Student */}
              <li><Link to="/courseeditions/gradeastudent" className="navbar-link"> Grade A Student</Link></li>
            </ul>
          </li>
        </ul>
      </nav>
    </div>
  );
};

export default NavBar;

