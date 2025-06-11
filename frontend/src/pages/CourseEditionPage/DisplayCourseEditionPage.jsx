import React from 'react';
import CourseEditionDisplay from "../../components/courseEditionComponent/CourseEditionDisplay";
import '../../styles/DisplayTeacherPage.css';
import NavBar from "../../components/NavBar";
import Footer from "../../components/Footer";

export default function DisplayCourseEditionPage() {
    return (
        <>
            <NavBar />
            <CourseEditionDisplay />
            <Footer/>
        </>
    );
}