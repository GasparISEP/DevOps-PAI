import React from "react";

export default function TeacherSelector({ teachers, value, onChange }) {
    return (
        <div className="form-group">
            <label className="form-label" htmlFor="teacher">Teacher</label>
            <select
                className="form-input"
                id="teacher"
                name="teacher"
                value={value}
                onChange={onChange}
                required
            >
                <option value="">Select a teacher</option>
                {teachers.map((teacher, index) => (
                    <option key={index} value={teacher.id}>
                        {teacher.name} - {teacher.email}
                    </option>
                ))}
            </select>
        </div>
    );
}