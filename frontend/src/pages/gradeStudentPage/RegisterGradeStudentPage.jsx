import React from 'react';
import GradeStudentForm from '../../components/gradeStudentComponent/GradeStudentForm';
import '../../styles/RegisterGradeStudentPage.css'; // 🔹 Alterado para o CSS correto
import NavBar from "../../components/NavBar";
import Footer from "../../components/Footer";

export default function RegisterGradeStudentPage() {
    return (
        <>
            <NavBar />
            <GradeStudentForm />
            <Footer />
        </>
    );
}
