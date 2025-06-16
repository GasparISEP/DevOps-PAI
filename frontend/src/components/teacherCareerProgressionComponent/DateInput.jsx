import React, { forwardRef } from 'react';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { format, parse } from 'date-fns';

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
    const parsedValue = value
        ? parse(value, 'dd-MM-yyyy', new Date())
        : null;

    return (
        <div className="form-group">
            <label className="form-label" htmlFor="date">Date</label>
            <DatePicker
                id="date"
                selected={parsedValue}
                onChange={(date) => {
                    const formatted = date ? format(date, 'dd-MM-yyyy') : '';
                    onChange(formatted);
                }}
                dateFormat="dd-MM-yyyy"
                placeholderText="Select a Date"
                customInput={<CustomDateInput value={value} />}
            />
            {error && <div className="form-error">{error}</div>}
        </div>
    );
}
