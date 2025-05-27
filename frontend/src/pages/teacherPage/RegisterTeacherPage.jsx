import React from 'react';
import TeacherForm from '../../components/teacherComponent/TeacherForm';
import '../../styles/RegisterTeacherPage.css';
import NavBar from "../../components/NavBar";

export default function RegisterTeacherPage() {
    return (
        <>
            <NavBar />
            <TeacherForm />
        </>

    );
}
