import React from 'react';
import UpdateTeacherCategoryForm from '../../components/teacherCareerProgressionComponent/UpdateTeacherCategoryForm';
import '../../styles/UpdateTeacherCategoryPage.css';
import NavBar from "../../components/NavBar";
import Footer from "../../components/Footer";

export default function UpdateTeacherCategoryPage() {
    return (
        <>
            <NavBar />
            <UpdateTeacherCategoryForm />
            <Footer/>
        </>

    );
}
