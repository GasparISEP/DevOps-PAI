import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import DurationSelector from '../../../components/courseComponent/DurationSelector';

describe('DurationSelector component', () => {
    const mockDurations = [1, 2];

    it('renders the select element with label', () => {
        render(<DurationSelector value="" onChange={() => {}} durations={mockDurations} />);
        expect(screen.getByLabelText(/duration/i)).toBeInTheDocument();
    });

    it('displays duration options correctly', () => {
        render(<DurationSelector value="" onChange={() => {}} durations={mockDurations} />);
        expect(screen.getByText('Select duration')).toBeInTheDocument();
        expect(screen.getByText('1 year')).toBeInTheDocument();
        expect(screen.getByText('2 years')).toBeInTheDocument();
    });

    it('calls onChange when a duration is selected', () => {
        const handleChange = jest.fn();
        render(<DurationSelector value="" onChange={handleChange} durations={mockDurations} />);
        const select = screen.getByLabelText(/duration/i);
        fireEvent.change(select, { target: { value: '2' } });
        expect(handleChange).toHaveBeenCalledTimes(1);
    });

    it('shows the correct selected value', () => {
        render(<DurationSelector value="1" onChange={() => {}} durations={mockDurations} />);
        const select = screen.getByLabelText(/duration/i);
        expect(select.value).toBe('1');
    });
});
