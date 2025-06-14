import React from 'react';
import StudentProgrammeEnrolmentForm from '../../components/studentComponent/StudentProgrammeEnrolmentForm';
import '../../styles/RegisterStudentPage.css';
import NavBar from "../../components/NavBar";
import Footer from "../../components/Footer";

export default function ProgrammeEnrolmentPage() {
    return (
        <>
            <NavBar />
            <StudentProgrammeEnrolmentForm />
            <Footer />
        </>
    );
}
