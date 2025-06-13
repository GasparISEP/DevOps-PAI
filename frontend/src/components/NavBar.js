import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../styles/NavBar.css';
import logoImage from "../assets/images/ISEP_logo.png";

const NavBar = () => {
  const [isTeacherOpen, setIsTeacherOpen] = useState(false);
  const [isStudentOpen, setIsStudentOpen] = useState(false);
  const [isOpenCourseEdition, setIsOpenCourseEdition] = useState(false);

  return (
      <div className="main-navbar-div">
        <nav className="navbar">
          <div className="logo-div">
            <Link to="/"><img className="logo-img" src={logoImage} alt="ISEP logo" /></Link>
          </div>
          <ul className="navbar-links-list-div">

            {/* Teacher Dropdown */}
            <li
                className="navbar-dropdown"
                onMouseEnter={() => setIsTeacherOpen(true)}
                onMouseLeave={() => setIsTeacherOpen(false)}
            >
            <span className="navbar-link" tabIndex={0}>
              <i className="fas fa-chalkboard-teacher"></i> Teacher
            </span>
              <ul className={`navbar-dropdown-menu${isTeacherOpen ? ' show' : ''}`}>
                <li>
                  <Link to="/teachers/register" className="navbar-link" onClick={() => setIsTeacherOpen(false)}>
                    Register Teacher
                  </Link>
                </li>
                <li>
                  <Link to="/teachers/display" className="navbar-link" onClick={() => setIsTeacherOpen(false)}>
                    Display Teachers
                  </Link>
                </li>
                <li>
                  <Link to="/teachers/updateTeacherCategory" className="navbar-link" onClick={() => setIsTeacherOpen(false)}>
                    Update Teacher Category
                  </Link>
                </li>
              </ul>
            </li>

            {/* Student Dropdown */}
            <li
                className="navbar-dropdown"
                onMouseEnter={() => setIsStudentOpen(true)}
                onMouseLeave={() => setIsStudentOpen(false)}
            >
            <span className="navbar-link" tabIndex={0}>
              <i className="fas fa-user-graduate"></i> Student
            </span>
              <ul className={`navbar-dropdown-menu${isStudentOpen ? ' show' : ''}`}>
                <li>
                  <Link to="/students/register" className="navbar-link" onClick={() => setIsStudentOpen(false)}>
                    Register Student
                  </Link>
                </li>
                <li>
                  <Link to="/students/enroll" className="navbar-link" onClick={() => setIsStudentOpen(false)}>
                    Enroll Student
                  </Link>
                </li>
                <li>
                  <Link to="/students/display" className="navbar-link" onClick={() => setIsStudentOpen(false)}>
                    Display Students
                  </Link>
                </li>
              </ul>
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

            {/* Course Edition Dropdown */}
            <li
                className="navbar-dropdown"
                onMouseEnter={() => setIsOpenCourseEdition(true)}
                onMouseLeave={() => setIsOpenCourseEdition(false)}
            >
            <span className="navbar-link" tabIndex={0}>
              <i className="fa-solid fa-book"></i> Course Edition
            </span>
              <ul className={`navbar-dropdown-menu${isOpenCourseEdition ? ' show' : ''}`}>
                <li>
                  <Link to="/courseeditions/register-grade-student" className="navbar-link">
                    Grade A Student
                  </Link>
                </li>
                <li>
                  <Link to="/courseeditions/display" className="navbar-link">
                    Display Course Editions
                  </Link>
                </li>
                <li>
                  <Link to="/course-editions/average-grade" className="navbar-link">
                    Average Grade
                  </Link>
                </li>
              </ul>
            </li>

          </ul>
        </nav>
      </div>
  );
};

export default NavBar;