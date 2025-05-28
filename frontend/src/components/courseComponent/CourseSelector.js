import React from 'react';

export default function CourseSelector({ courses, value, onChange }) {
    return (
        <div className="form-group">
            <label className="form-label" htmlFor="course">Course</label>
            <select
                className="form-input"
                id="course"
                name="course"
                value={value}
                onChange={onChange}
                required
            >
                <option value="">Select a course</option>
                {courses.map((c, index) => (
                    <option key={index} value={c.acronym}>
                        {c.acronym} - {c.name}
                    </option>
                ))}
            </select>
        </div>
    );
}
