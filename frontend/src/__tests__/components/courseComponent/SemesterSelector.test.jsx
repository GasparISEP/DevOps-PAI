import React from 'react';
import {fireEvent, render, screen} from '@testing-library/react';
import '@testing-library/jest-dom';
import SemesterSelector from '../../../components/courseComponent/SemesterSelector'; // Adjust the path if necessary

describe('SemesterSelector', () => {
    const mockSemesters = ['Fall 2024', 'Spring 2025'];
    const mockOnChange = jest.fn();

    beforeEach(() => {
        mockOnChange.mockClear();
    });

    // Positive Scenario
    test('renders correctly and triggers onChange', () => {
        render(<SemesterSelector semesters={mockSemesters} value="" onChange={mockOnChange} />);

        const select = screen.getByRole('combobox');
        expect(select).toBeInTheDocument();
        expect(screen.getByText('Fall 2024')).toBeInTheDocument();
        expect(screen.getByText('Spring 2025')).toBeInTheDocument();

        fireEvent.change(select, { target: { value: 'Fall 2024' } });
        expect(mockOnChange).toHaveBeenCalledTimes(1);
    });

    // Negative Scenario: required validation
    test('select is required and shows default prompt if value is empty', () => {
        render(<SemesterSelector semesters={mockSemesters} value="" onChange={mockOnChange} />);
        const select = screen.getByRole('combobox');

        expect(select).toBeRequired();
        expect(select.value).toBe('');
        fireEvent.invalid(select); // simulate form submit
        expect(select.validationMessage).not.toBe('');
    });

    //  Edge Case 1: Empty list
    test('renders only default option if semesters is empty', () => {
        render(<SemesterSelector semesters={[]} value="" onChange={mockOnChange} />);
        const options = screen.getAllByRole('option');
        expect(options).toHaveLength(1);
        expect(options[0].textContent).toBe('Select a semester');
    });

    //  Edge Case 2: semesters = null
    test('handles null semesters', () => {
        render(<SemesterSelector semesters={null} value="" onChange={mockOnChange} />);
        const options = screen.getAllByRole('option');
        expect(options).toHaveLength(1);
        expect(options[0].textContent).toBe('Select a semester');
    });

    //  Edge Case 3: semesters = undefined
    test('handles undefined semesters', () => {
        render(<SemesterSelector semesters={undefined} value="" onChange={mockOnChange} />);
        const options = screen.getAllByRole('option');
        expect(options).toHaveLength(1);
        expect(options[0].textContent).toBe('Select a semester');
    });

    //  Edge Case 4: Duplicate semester names
    test('renders duplicate semesters if present', () => {
        const dupSemesters = ['Fall 2024', 'Fall 2024'];
        render(<SemesterSelector semesters={dupSemesters} value="" onChange={mockOnChange} />);
        expect(screen.getAllByText('Fall 2024')).toHaveLength(2);
    });

    // Edge Case 5: Pre-selected value is shown
    test('correct semester is selected when value prop is set', () => {
        render(<SemesterSelector semesters={mockSemesters} value="Spring 2025" onChange={mockOnChange} />);
        const select = screen.getByRole('combobox');
        expect(select.value).toBe('Spring 2025');
    });


    test('handles non-array semesters (object)', () => {
        render(<SemesterSelector semesters={{}} value="" onChange={mockOnChange} />);
        const options = screen.getAllByRole('option');
        expect(options).toHaveLength(1);
    });


    test('handles number as semesters', () => {
        render(<SemesterSelector semesters={1234} value="" onChange={mockOnChange} />);
        const options = screen.getAllByRole('option');
        expect(options).toHaveLength(1);
    });
});
