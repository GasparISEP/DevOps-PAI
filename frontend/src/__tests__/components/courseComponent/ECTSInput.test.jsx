import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import ECTSInput from '../../../components/courseComponent/ECTSInput';

describe('ECTSInput', () => {
    it('renders correctly with default props', () => {
        render(<ECTSInput value="" onChange={() => {}} />);
        const input = screen.getByLabelText(/ECTS/i);
        expect(input).toBeInTheDocument();
        expect(input).toHaveAttribute('placeholder', 'Enter ECTS');
    });

    it('shows maxECTS in placeholder if provided', () => {
        render(<ECTSInput value="" onChange={() => {}} maxECTS={30} />);
        expect(screen.getByPlaceholderText('Enter ECTS (max 30)')).toBeInTheDocument();
    });

    it('calls onChange when the input changes', () => {
        const handleChange = jest.fn();
        render(<ECTSInput value="" onChange={handleChange} />);
        const input = screen.getByLabelText(/ECTS/i);
        fireEvent.change(input, { target: { value: '5' } });
        expect(handleChange).toHaveBeenCalledTimes(1);
    });

    it('input becomes invalid if value exceeds maxECTS', () => {
        render(<ECTSInput value="40" onChange={() => {}} maxECTS={30} />);
        const input = screen.getByLabelText(/ECTS/i);
        fireEvent.change(input, { target: { value: '40' } });

        expect(input.checkValidity()).toBe(false);
    });

    it('clears custom validity if value is within maxECTS', () => {
        render(<ECTSInput value="25" onChange={() => {}} maxECTS={30} />);
        const input = screen.getByLabelText(/ECTS/i);
        fireEvent.change(input, { target: { value: '25' } });
        expect(input.validationMessage).toBe('');
    });
});
