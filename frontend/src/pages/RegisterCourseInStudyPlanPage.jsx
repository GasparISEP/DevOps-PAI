import React from 'react';
import CourseForm from '../components/courseComponent/CourseForm';
import '../styles/RegisterCourseInStudyPlanPage.css';
import NavBar from "../components/NavBar";

export default function RegisterCourseInStudyPlanPage() {
    return (
        <>
            <NavBar />
            <CourseForm />
        </>

    );
}