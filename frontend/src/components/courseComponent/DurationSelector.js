import React from 'react';

export default function DurationSelector({ value, onChange, durations = [] }) {
    return (
        <div className="form-group">
            <label className="form-label" htmlFor="duration">Duration (years)</label>
            <select
                className="form-input"
                id="duration"
                name="duration"
                value={value}
                onChange={onChange}
                required
            >
                <option value="">Select duration</option>
                {durations.map((duration) => (
                    <option key={duration} value={duration}>
                        {duration} {duration === 1 ? 'year' : 'years'}
                    </option>
                ))}
            </select>
        </div>
    );
}
