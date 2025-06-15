import React from "react";

export default function CourseEditionSelector({ courseEditions, value, onChange }) {
    return (
        <div className="form-group">
            <label className="form-label" htmlFor="courseEdition">Course Edition</label>
            <select
                className="form-input"
                id="courseEdition"
                name="courseEditionId"
                value={value}
                onChange={onChange}
                required
            >
                <option value="">Select a course edition</option>
                {courseEditions.map((ce, index) => (
                    <option key={index} value={ce.courseEditionGeneratedID}>
                        {ce.courseName} ({ce.programmeAcronym})
                    </option>
                ))}
            </select>
        </div>
    );
}