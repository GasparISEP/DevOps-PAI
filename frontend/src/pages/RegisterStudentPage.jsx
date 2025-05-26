import React from 'react';
import StudentForm from '../components/studentComponent/StudentForm';
import '../styles/RegisterStudentPage.css';
import NavBar from "../components/NavBar";

export default function RegisterStudentPage() {
    return (
        <>
            <NavBar />
            <StudentForm />
        </>

    );
}
