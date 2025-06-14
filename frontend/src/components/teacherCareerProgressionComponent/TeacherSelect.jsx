import React from 'react';

export default function TeacherSelect({ value, onChange, options, error }) {
    return (
        <>
            <label className="form-label" htmlFor="teacher">Teacher</label>
            <select className="form-input" id="teacher" name="teacher" value={value} onChange={onChange}>
                <option value="">Select a Teacher</option>
                {options.map((teacher) => (
                    <option key={teacher.id} value={teacher.id}>
                        {teacher.id} - {teacher.name}
                    </option>
                ))}
            </select>
            {error && <div className="form-error">{error}</div>}
        </>
    );
}
