import React from 'react';

export default function ECTSInput({ value, onChange, maxECTS }) {
    return (
        <div className="form-group">
            <label className="form-label" htmlFor="qtdECTS">ECTS</label>
            <input
                className="form-input"
                type="number"
                id="qtdECTS"
                name="qtdECTS"
                placeholder={maxECTS ? `Enter ECTS (max ${maxECTS})` : "Enter ECTS"}
                value={value}
                onChange={(e) => {
                    const val = e.target.value;
                    e.target.setCustomValidity('');
                    if (maxECTS && parseInt(val) > maxECTS) {
                        e.target.setCustomValidity(`Value must be less than or equal to ${maxECTS}.`);
                    }
                    onChange(e);
                }}
                required
                min="1"
                max={maxECTS || undefined}
                step="1"
            />
        </div>
    );
}
