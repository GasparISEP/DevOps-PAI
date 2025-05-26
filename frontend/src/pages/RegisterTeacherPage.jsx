import React from 'react';
import TeacherForm from '../components/teacherComponent/TeacherForm';
import '../styles/RegisterTeacherPage.css';
import NavBar from "../components/NavBar"; // ou cria um novo CSS para programmes

export default function RegisterTeacherPage() {
    return (
        <>
            <NavBar />
            <TeacherForm />
        </>

    );
}
