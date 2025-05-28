import React from 'react';
import StudentForm from '../components/studentComponent/StudentForm';
import '../styles/RegisterStudentPage.css';
import NavBar from "../components/NavBar";
import Footer from "../components/Footer";

export default function RegisterStudentPage() {
    return (
        <>
            <NavBar />
            <StudentForm />
            <Footer/>
        </>

    );
}
