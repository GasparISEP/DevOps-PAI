import React from 'react';
import '../../styles/Form.css'; // usa o mesmo estilo do form!

export default function StudentProgrammeEditions({ editions }) {
    if (!editions.length) {
        return <p>No programme editions enrolled.</p>;
    }

    return (
        <div className="form">
            {editions.map((e, idx) => (
                <div key={idx} className="form-div" style={{ marginBottom: '2rem' }}>
                    <h2 style={{ marginBottom: '1rem' }}>Programme Edition</h2>

                    <div className="row">
                        <label className="label">Programme</label>
                        <div className="field">{e.programmeAcronym}</div>
                    </div>

                    <div className="row">
                        <label className="label">School Year</label>
                        <div className="field">{e.schoolYearDescription}</div>
                    </div>
                </div>
            ))}
        </div>
    );
}