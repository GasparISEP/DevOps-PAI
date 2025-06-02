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
                placeholder={maxECTS !== null && maxECTS !== undefined
                    ? `Enter ECTS (max ${maxECTS})`
                    : "Enter ECTS"}
                value={value}
                onChange={(e) => {
                    let val = e.target.value;
                    const parsedVal = parseInt(val, 10);

                    if (!Number.isNaN(parsedVal) && typeof maxECTS === 'number' && parsedVal > maxECTS) {
                        val = String(maxECTS);
                        e.target.setCustomValidity(`Value must be less than or equal to ${maxECTS}.`);
                    } else {
                        e.target.setCustomValidity('');
                    }

                    onChange({ target: { name: e.target.name, value: val } });
                }}
                required
                min="1"
                max={typeof maxECTS === 'number' ? maxECTS : undefined}
                step="1"
            />
            {typeof maxECTS === 'number' && (
                <small className="form-hint" style={{ color: '#555' }}>
                    Remaining ECTS for this semester: <strong>{maxECTS}</strong>
                </small>
            )}
        </div>
    );
}

