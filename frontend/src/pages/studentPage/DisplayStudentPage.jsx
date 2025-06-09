import React from 'react';
import StudentDisplay from '../../components/studentComponent/StudentDisplay';
import '../../styles/DisplayStudentPage.css';
import NavBar from "../../components/NavBar";
import Footer from "../../components/Footer";

export default function DisplayStudentPage() {
    return (
        <>
            <NavBar />
            <StudentDisplay />
            <Footer/>
        </>
    );
}