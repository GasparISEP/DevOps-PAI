import React from 'react';

export default function CurricularYearSelector({ years, value, onChange }) {
    return (
        <div className="form-group">
            <label className="form-label" htmlFor="curricularYear">Curricular Year</label>
            <select
                className="form-input"
                id="curricularYear"
                name="curricularYear"
                value={value}
                onChange={onChange}
                required
            >
                <option value="">Select a curricular year</option>
                {years.map((year, index) => (
                    <option key={index} value={year}>{year}</option>
                ))}
            </select>
        </div>
    );
}