import React from 'react';
import TeacherDisplay from '../../components/teacherComponent/TeacherDisplay';
import '../../styles/DisplayTeacherPage.css';
import NavBar from "../../components/NavBar";
import Footer from "../../components/Footer";

export default function DisplayTeacherPage() {
    return (
        <>
            <NavBar />
            <TeacherDisplay />
            <Footer/>
        </>
    );
}