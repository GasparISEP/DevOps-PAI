import React from 'react';
import { render, screen } from '@testing-library/react';
import DateInput from '../../../components/teacherCareerProgressionComponent/DateInput';
import '@testing-library/jest-dom';
import userEvent from '@testing-library/user-event';
import { jest } from '@jest/globals';

let mockDate = new Date('2025-01-01');

jest.mock('react-datepicker', () => {
    const React = require('react');
    return {
        __esModule: true,
        default: ({ onChange, customInput, placeholderText }) => {
            return React.cloneElement(customInput, {
                value: mockDate ? mockDate.toISOString().split('T')[0] : '',
                onClick: () => onChange(mockDate),
                onChange: () => {},
                placeholder: placeholderText,
            });
        },
    };
});


describe('DateInput Component', () => {
    it('renders with placeholder and no value', () => {
        render(<DateInput value={null} onChange={jest.fn()} error={null} />);
        expect(screen.getByPlaceholderText('Select a Date')).toBeInTheDocument();
    });

    it('renders with a selected date', () => {
        render(<DateInput value="2025-06-14" onChange={jest.fn()} error={null} />);
        const input = screen.getByDisplayValue('2025-01-01');
        expect(input).toBeInTheDocument();
    });

    it('calls onChange with formatted date when date is selected', async () => {
        mockDate = new Date('2025-01-01');
        const handleChange = jest.fn();
        render(<DateInput value={null} onChange={handleChange} error={null} />);
        const input = screen.getByPlaceholderText('Select a Date');
        await userEvent.click(input);
        expect(handleChange).toHaveBeenCalledWith('01-01-2025');
    });

    it('renders error message when error prop is passed', () => {
        render(<DateInput value={null} onChange={jest.fn()} error="Required" />);
        expect(screen.getByText('Required')).toBeInTheDocument();
    });

    it('has correct styles on placeholder and value', () => {
        render(<DateInput value={null} onChange={jest.fn()} error={null} />);
        const input = screen.getByPlaceholderText('Select a Date');
        expect(input).toHaveClass('custom-date-input');
        expect(input).toHaveStyle('color: #333');
    });

    it('does not render error message when error is null', () => {
        render(<DateInput value={null} onChange={jest.fn()} error={null} />);
        const errorMessage = screen.queryByText('Required');
        expect(errorMessage).not.toBeInTheDocument();
    });

    it('calls onChange with empty string when date is null', async () => {
        mockDate = null; // <- força o branch alternativo

        const handleChange = jest.fn();
        render(<DateInput value={null} onChange={handleChange} error={null} />);
        const input = screen.getByPlaceholderText('Select a Date');
        await userEvent.click(input);
        expect(handleChange).toHaveBeenCalledWith('');
    });


});
