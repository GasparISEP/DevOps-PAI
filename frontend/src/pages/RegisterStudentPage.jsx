import React from 'react';
import StudentForm from '../components/studentComponent/StudentForm';
import '../styles/RegisterStudentPage.css';

export default function RegisterStudentPage() {
    return (
        <div className="right-panel">
            <h1>Register Student</h1>
            <StudentForm />
        </div>
    );
}
