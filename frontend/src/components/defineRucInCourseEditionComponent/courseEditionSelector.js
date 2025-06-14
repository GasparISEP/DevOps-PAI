import React from "react";

export default function CourseEditionSelector({ courseEditions, value, onChange }) {
    return (
        <div className="form-group">
            <label className="form-label" htmlFor="courseEdition">Course Edition</label>
            <select
                className="form-input"
                id="courseEdition"
                name="courseEdition"
                value={value}
                onChange={onChange}
                required
            >
                <option value="">Select a course edition</option>
                {courseEditions.map((ce, index) => (
                    <option key={index} value={ce.id}>
                        {ce.name} - {ce.startDate} to {ce.endDate}
                    </option>
                ))}
            </select>
        </div>
    );
}