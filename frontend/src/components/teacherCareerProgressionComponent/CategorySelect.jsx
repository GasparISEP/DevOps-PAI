import React from 'react';

export default function CategorySelect({ value, onChange, options, error }) {
    return (
        <>
            <label className="form-label" htmlFor="teacherCategory">Teacher Category</label>
            <select className="form-input" id="teacherCategory" name="teacherCategory" value={value} onChange={onChange}>
                <option value="">-- Select a Category --</option>
                {options.map((cat) => (
                    <option key={cat.id} value={cat.id}>{cat.name}</option>
                ))}
            </select>
            {error && <div className="form-error">{error}</div>}
        </>
    );
}
