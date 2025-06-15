import React from "react";

export default function SchoolYearSelector({ schoolYears, value, onChange, name }) {
    return (
        <div className="form-group">
            <label className="form-label" htmlFor={name}>School Year</label>
            <select
                className="form-input"
                id={name}
                name={name}
                value={value}
                onChange={onChange}
                required
            >
                <option value="">Select a school year</option>
                {schoolYears.map((year) => (
                    <option key={year.id} value={year.id}>
                        {year.description}
                    </option>
                ))}
            </select>
        </div>
    );
}
