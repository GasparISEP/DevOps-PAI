import React from 'react';
import ProgrammeForm from '../components/programmeComponent/ProgrammeForm';
import '../styles/RegisterStudentPage.css'; // ou cria um novo CSS para programmes

export default function RegisterProgrammePage() {
    return (
        <div className="right-panel">
            <h1>Register Programme</h1>
            <ProgrammeForm />
        </div>
    );
}
