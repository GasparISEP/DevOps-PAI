import React from 'react';
import EnrollStudentForm from '../../components/studentComponent/EnrollStudentForm';
import NavBar from "../../components/NavBar";
import Footer from "../../components/Footer";
import '../../styles/RegisterStudentPage.css';

export default function EnrollStudentPage() {
    return (
        <>
            <NavBar />
            <EnrollStudentForm />
            <Footer />
        </>
    );
}