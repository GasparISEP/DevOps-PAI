import React, { forwardRef } from 'react';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

const CustomDateInput = forwardRef(({ value, onClick, onChange, placeholder }, ref) => {
    return (
        <>
            <style>
                {`
          input.custom-date-input::placeholder {
            color: #333 !important;
            opacity: 1 !important;
          }
        `}
            </style>
            <input
                type="text"
                onClick={onClick}
                onChange={onChange}
                value={value}
                placeholder={placeholder}
                ref={ref}
                className="form-input custom-date-input"
                style={{
                    color: '#333',
                    fontStyle: value ? 'normal' : 'normal'
                }}
            />
        </>
    );
});

export default function DateInput({ value, onChange, error }) {
    return (
        <div className="form-group">
            <label className="form-label" htmlFor="date">Date</label>
            <DatePicker
                id="date"
                selected={value ? new Date(value) : null}
                onChange={(date) => {
                    const formatted = date?.toISOString().split('T')[0];
                    onChange(formatted);
                }}
                dateFormat="dd-MM-yyyy"
                placeholderText="Select a Date"
                customInput={<CustomDateInput />}
            />
            {error && <div className="form-error">{error}</div>}
        </div>
    );
}
