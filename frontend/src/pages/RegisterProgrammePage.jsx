import React from 'react';
import ProgrammeForm from '../components/programmeComponent/ProgrammeForm';
import '../styles/RegisterStudentPage.css';
import NavBar from "../components/NavBar";
import Footer from "../components/Footer";

export default function RegisterProgrammePage() {
    return (
        <>
            <NavBar />
            <ProgrammeForm />
            <Footer/>
        </>

    );
}
