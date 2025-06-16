import React from "react";

export default function CourseEditionSelector({ courseEditions, value, onChange, name }) {
    return (
        <div className="form-group">
            <label className="form-label" htmlFor={name}>Course Edition / Programme Edition </label>
            <select
                className="form-input"
                id={name}
                name={name}
                value={value}
                onChange={onChange}
                required
            >
                <option value="">Select Course Edition</option>
                {courseEditions.map(ce => (
                    <option key={ce.courseEditionGeneratedID} value={ce.courseEditionGeneratedID}>
                        {ce.courseName} / {ce.programmeName}
                    </option>
                ))}
            </select>
        </div>
    );
}