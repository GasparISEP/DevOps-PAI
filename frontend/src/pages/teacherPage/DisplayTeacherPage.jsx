import React from 'react';
import TeacherDisplay from '../../components/teacherComponent/TeacherDisplay';
import '../../styles/DisplayTeacherPage.css';
import NavBar from "../../components/NavBar";

export default function DisplayTeacherPage() {
    return (
        <>
            <NavBar />
            <TeacherDisplay />
        </>
    );
}