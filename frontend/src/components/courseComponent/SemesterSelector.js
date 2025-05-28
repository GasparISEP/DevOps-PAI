import React from 'react';

export default function SemesterSelector({ semesters, value, onChange }) {
    return (
        <div className="form-group">
            <label className="form-label" htmlFor="semester">Semester</label>
            <select
                className="form-input"
                id="semester"
                name="semester"
                value={value}
                onChange={onChange}
                required
            >
                <option value="">Select a semester</option>
                {Array.isArray(semesters) &&
                    semesters.map((sem, index) => (
                    <option key={index} value={sem}>{sem}</option>
                ))}
                    </select>
                    </div>
                    );
                }
