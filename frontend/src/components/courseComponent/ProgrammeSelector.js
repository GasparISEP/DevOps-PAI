import React from 'react';

export default function ProgrammeSelector({ programmes, value, onChange }) {
    return (
        <div className="form-group">
            <label className="form-label" htmlFor="programme">Programme</label>
            <select
                className="form-input"
                id="programme"
                name="programme"
                value={value}
                onChange={onChange}
                required
            >
                <option value="">Select a programme</option>
                {(programmes || []).map((p, index) => (
                    <option key={index} value={p.acronym}>
                        {p.acronym} - {p.name}
                    </option>
                ))}
            </select>
        </div>
    );
}
