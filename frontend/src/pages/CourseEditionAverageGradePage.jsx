import React from 'react';
import CourseEditionAverageGradeForm from '../components/courseEditionAverageGradeComponent/CourseEditionAverageGradeForm';
import '../styles/CourseEditionAverageGradePage.css';
import NavBar from "../components/NavBar";
import Footer from "../components/Footer";

export default function CourseEditionAverageGradePage() {
    return (
        <>
            <NavBar />
            <CourseEditionAverageGradeForm />
            <Footer/>
        </>

    );
}
