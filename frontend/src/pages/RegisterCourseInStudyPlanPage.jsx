import React from 'react';
import CourseForm from '../components/courseComponent/CourseForm';
import '../styles/RegisterCourseInStudyPlanPage.css';
import NavBar from "../components/NavBar";
import Footer from "../components/Footer";

export default function RegisterCourseInStudyPlanPage() {
    return (
        <>
            <NavBar />
            <CourseForm />
            <Footer/>
        </>

    );
}