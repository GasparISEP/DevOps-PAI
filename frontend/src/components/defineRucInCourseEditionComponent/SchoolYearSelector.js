import React from "react";

export default function SchoolYearSelector({ schoolYears, value, onChange }) {
    return (
        <div className="form-group">
            <label className="form-label" htmlFor="schoolYear">School Year</label>
            <select
                className="form-input"
                id="schoolYear"
                name="schoolYear"
                value={value}
                onChange={onChange}
                required
            >
                <option value="">Select a school year</option>
                {schoolYears.map((year, index) => (
                    <option key={index} value={year}>
                        {year}
                    </option>
                ))}
            </select>
        </div>
    );
}
